package com.dev.farmmanager.service.cropcycle;

import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.entity.CropCycleControl;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import com.dev.farmmanager.exception.handler.CropCycleControlNotFoundException;
import com.dev.farmmanager.repository.CropCycleControlRepository;
import com.dev.farmmanager.repository.TransactionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CropCycleControlServiceImpl implements CropCycleControlService {

    private final CropCycleControlRepository repository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recalculate(@NonNull final Integer cropCycleId) {
        CropCycleControl control = repository.findByCropCycleId(cropCycleId)
                .orElseThrow(CropCycleControlNotFoundException::new);

        CropCycle cropCycle = control.getCropCycle();

        BigDecimal investment = transactionRepository.sumTotalValueByCropCycleIdAndType(cropCycleId, TransactionType.EXPENSE);
        BigDecimal revenue = transactionRepository.sumTotalValueByCropCycleIdAndType(cropCycleId, TransactionType.INCOME);

        BigDecimal roi = calculateRoi(investment, revenue);

        control.setCurrentInvestment(investment);
        control.setCurrentRevenue(revenue);
        control.setCurrentRoi(roi);
        control.setProgressPercentage(calculateProgress(cropCycle));
        control.setHealthScore(calculateHealthScore(cropCycle, investment, roi));
        control.setAlertsCount(calculateAlerts(cropCycle, investment, roi));
        control.setLastCalculatedAt(Instant.now());

        repository.save(control);
    }

    private BigDecimal calculateRoi(BigDecimal investment, BigDecimal revenue) {
        if (investment.compareTo(BigDecimal.ZERO) > 0) {
            return BigDecimal.ZERO;
        }
        return revenue.subtract(investment)
                .divide(investment, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateProgress(CropCycle cropCycle) {
        CropCycleStatus status = cropCycle.getStatus();

        if (status == CropCycleStatus.FINISHED || status == CropCycleStatus.CANCELLED) {
            return new BigDecimal("100.00");
        }

        LocalDate endDate = cropCycle.getEndDate();
        if (endDate != null) {
            LocalDate startDate = cropCycle.getStartDate();
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            if (totalDays <= 0) {
                return BigDecimal.ZERO;
            }
            long elapsed = ChronoUnit.DAYS.between(startDate, LocalDate.now());
            double progress = Math.min(100.0, Math.max(0.0, (double) elapsed / totalDays * 100));
            return BigDecimal.valueOf(progress).setScale(2, RoundingMode.HALF_UP);
        }

        // usa progresso por status de fallback
        return switch (status) {
            case PLANNING -> BigDecimal.ZERO;
            case ACTIVE -> BigDecimal.valueOf(25);
            case PLANTING -> BigDecimal.valueOf(50);
            case HARVESTING -> BigDecimal.valueOf(75);
            default -> BigDecimal.ZERO;
        };
    }

    private int calculateHealthScore(CropCycle cropCycle, BigDecimal investment, BigDecimal roi) {
        double score = 100.0;
        LocalDate today = LocalDate.now();
        CropCycleStatus status = cropCycle.getStatus();

        // Orçamento: cada 50% de estouro remove ~35 pts
        BigDecimal plannedBudget = cropCycle.getPlannedBudget();
        if (plannedBudget != null && plannedBudget.compareTo(BigDecimal.ZERO) > 0
                && investment.compareTo(plannedBudget) > 0) {
            double overrunRatio = investment.subtract(plannedBudget)
                    .divide(plannedBudget, 4, RoundingMode.HALF_UP)
                    .doubleValue();
            score -= Math.min(35.0, overrunRatio * 70.0);
        }

        // Prazo (até -35): vencido sem FINISHED/CANCELLED = -35; <15% tempo restante = proporcional até -20
        LocalDate endDate = cropCycle.getEndDate();
        if (endDate != null && status != CropCycleStatus.FINISHED && status != CropCycleStatus.CANCELLED) {
            long daysRemaining = ChronoUnit.DAYS.between(today, endDate);
            if (daysRemaining < 0) {
                score -= 35.0;
            } else {
                long totalDays = ChronoUnit.DAYS.between(cropCycle.getStartDate(), endDate);
                if (totalDays > 0 && status != CropCycleStatus.HARVESTING) {
                    double remainingRatio = (double) daysRemaining / totalDays;
                    if (remainingRatio < 0.15) {
                        score -= (0.15 - remainingRatio) / 0.15 * 20.0;
                    }
                }
            }
        }

        // ROI (até -30): abs(roi) * 0.30 — ROI de -100% remove 30 pts
        if (investment.compareTo(BigDecimal.ZERO) > 0 && roi.compareTo(BigDecimal.ZERO) < 0) {
            score -= Math.min(30.0, Math.abs(roi.doubleValue()) * 0.30);
        }

        return Math.max(0, Math.min(100, (int) Math.round(score)));
    }

    private int calculateAlerts(CropCycle cropCycle, BigDecimal investment, BigDecimal roi) {
        int alerts = 0;
        LocalDate today = LocalDate.now();
        CropCycleStatus status = cropCycle.getStatus();
        boolean isActive = status != CropCycleStatus.FINISHED && status != CropCycleStatus.CANCELLED;

        if (cropCycle.getPlannedBudget() != null
                && investment.compareTo(cropCycle.getPlannedBudget()) > 0) {
            alerts++;
        }

        if (cropCycle.getEndDate() != null && isActive) {
            long daysUntilEnd = ChronoUnit.DAYS.between(today, cropCycle.getEndDate());
            if (daysUntilEnd < 0) {
                alerts++; // prazo vencido
            } else if (daysUntilEnd <= 7 && status != CropCycleStatus.HARVESTING) {
                alerts++; // prazo próximo
            }
        }

        if (investment.compareTo(BigDecimal.ZERO) > 0 && roi.compareTo(BigDecimal.ZERO) < 0) {
            alerts++; // ROI negativo
        }

        return alerts;
    }
}
