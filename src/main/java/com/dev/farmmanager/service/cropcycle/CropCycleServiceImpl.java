package com.dev.farmmanager.service.cropcycle;

import com.dev.farmmanager.domain.dto.cropcycle.CropCyclePageDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCycleRowDto;
import com.dev.farmmanager.domain.dto.cropcycle.CropCycleSummaryDto;
import com.dev.farmmanager.domain.entity.CropCycle;
import com.dev.farmmanager.domain.entity.CropCycleControl;
import com.dev.farmmanager.domain.entity.User;
import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.domain.enumeration.MeasurementUnit;
import com.dev.farmmanager.domain.payload.cropcycle.CropCyclePayload;
import com.dev.farmmanager.exception.handler.CropCycleNotFoundException;
import com.dev.farmmanager.exception.handler.InvalidMeasurementUnitPairException;
import com.dev.farmmanager.exception.handler.UserNotFoundException;
import com.dev.farmmanager.mapper.CropCycleMapper;
import com.dev.farmmanager.repository.CropCycleControlRepository;
import com.dev.farmmanager.repository.CropCycleRepository;
import com.dev.farmmanager.security.SecurityUtils;
import com.dev.farmmanager.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CropCycleServiceImpl implements CropCycleService {

    private final CropCycleRepository repository;
    private final CropCycleControlRepository controlRepository;
    private final CropCycleMapper mapper;
    private final UserService userService;

    @Override
    public CropCyclePageDto findAll() {
        Integer userId = SecurityUtils.getCurrentUserId();
        List<CropCycleRowDto> rows = repository.findAllRowsByUserId(userId);

        long activeCyclesCount = rows.stream()
                .filter(r -> CropCycleStatus.ACTIVE == r.status())
                .count();

        BigDecimal totalPlantedArea = rows.stream()
                .map(CropCycleRowDto::plantedArea)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OptionalDouble avgProgress = rows.stream()
                .map(CropCycleRowDto::progressPercentage)
                .filter(Objects::nonNull)
                .mapToDouble(BigDecimal::doubleValue)
                .average();

        OptionalDouble avgRoi = rows.stream()
                .map(CropCycleRowDto::currentRoi)
                .filter(Objects::nonNull)
                .mapToDouble(BigDecimal::doubleValue)
                .average();

        CropCycleSummaryDto summary = new CropCycleSummaryDto(
                activeCyclesCount,
                totalPlantedArea,
                avgProgress.isPresent()
                        ? BigDecimal.valueOf(avgProgress.getAsDouble()).setScale(2, RoundingMode.HALF_UP)
                        : BigDecimal.ZERO,
                avgRoi.isPresent()
                        ? BigDecimal.valueOf(avgRoi.getAsDouble()).setScale(2, RoundingMode.HALF_UP)
                        : BigDecimal.ZERO
        );

        return new CropCyclePageDto(summary, rows);
    }

    @Override
    public List<CropCycle> findByStatus(CropCycleStatus status) {
        return repository.findAllByUserIdAndStatus(SecurityUtils.getCurrentUserId(), status);
    }

    @Override
    public List<CropCycle> findByDateBetween(LocalDate startDate, LocalDate endDate) {
        return repository.findAllByUserIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(SecurityUtils.getCurrentUserId(), startDate, endDate);
    }

    @Override
    public Optional<CropCycle> getById(Integer id) {
        return repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CropCycle create(@NonNull final Integer userId, @NonNull final CropCyclePayload payload) {
        if (payload.measurementUnit() == MeasurementUnit.PES && payload.plantCount() == null) {
            throw new InvalidMeasurementUnitPairException();
        }
        if (payload.measurementUnit() != MeasurementUnit.PES && payload.plantCount() != null) {
            throw new InvalidMeasurementUnitPairException();
        }

        User user = userService.getById(userId).orElseThrow(UserNotFoundException::new);

        CropCycle cropCycle = mapper.toEntity(payload);
        cropCycle.setUser(user);
        CropCycle saved = repository.save(cropCycle);

        // TODO: CropCycleControl será recalculado automaticamente ao registrar Transactions.
        //       Quando o TransactionService criar/alterar/deletar uma Transaction vinculada a este ciclo,
        //       deverá chamar CropCycleControlService.recalculate(cropCycleId), que somará EXPENSE →
        //       currentInvestment, INCOME → currentRevenue e recalculará currentRoi e progressPercentage.
        CropCycleControl control = new CropCycleControl();
        control.setCropCycle(saved);
        controlRepository.save(control);

        return saved;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CropCycle update(@NonNull final Integer id, @NonNull final CropCyclePayload payload) {
        if (payload.measurementUnit() == MeasurementUnit.PES && payload.plantCount() == null) {
            throw new InvalidMeasurementUnitPairException();
        }
        if (payload.measurementUnit() != MeasurementUnit.PES && payload.plantCount() != null) {
            throw new InvalidMeasurementUnitPairException();
        }

        CropCycle cropCycle = repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId())
                .orElseThrow(CropCycleNotFoundException::new);

        cropCycle.setName(payload.name().trim());
        cropCycle.setCrop(payload.crop());
        cropCycle.setPlantedArea(payload.plantedArea());
        cropCycle.setMeasurementUnit(payload.measurementUnit());
        cropCycle.setPlantCount(payload.plantCount());
        cropCycle.setPlannedBudget(payload.plannedBudget());
        cropCycle.setTargetYield(payload.targetYield());
        cropCycle.setStatus(payload.status());
        cropCycle.setStartDate(payload.startDate());
        cropCycle.setEndDate(payload.endDate());

        return repository.save(cropCycle);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        CropCycle cropCycle = repository.findByIdAndUserId(id, SecurityUtils.getCurrentUserId())
                .orElseThrow(CropCycleNotFoundException::new);
        repository.delete(cropCycle);
    }

}
