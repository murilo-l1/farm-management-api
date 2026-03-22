package com.dev.farmmanager.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = CropCycleControl.TABLE_NAME)
public class CropCycleControl {

    protected static final String TABLE_NAME = "crop_cycle_control";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected Integer id;

    @Column(name = "crop_cycle_id", updatable = false, insertable = false)
    private Integer cropCycleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_cycle_id", unique = true)
    private CropCycle cropCycle;

    @Column(name = "current_investment", precision = 15, scale = 2)
    private BigDecimal currentInvestment = BigDecimal.ZERO;

    @Column(name = "current_revenue", precision = 15, scale = 2)
    private BigDecimal currentRevenue = BigDecimal.ZERO;

    @Column(name = "current_roi", precision = 15, scale = 2)
    private BigDecimal currentRoi = BigDecimal.ZERO;

    @Column(name = "health_score")
    private Integer healthScore = 100;

    @Column(name = "progress_percentage", precision = 5, scale = 2)
    private BigDecimal progressPercentage = BigDecimal.ZERO;

    @Column(name = "alerts_count")
    private Integer alertsCount = 0;

    @Column(name = "last_calculated_at")
    private Instant lastCalculatedAt;

}
