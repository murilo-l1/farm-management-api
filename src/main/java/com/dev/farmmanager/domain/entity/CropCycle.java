package com.dev.farmmanager.domain.entity;

import com.dev.farmmanager.domain.enumeration.CropCycleStatus;
import com.dev.farmmanager.domain.enumeration.MeasurementUnit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;

import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = CropCycle.TABLE_NAME)
@Setter
@Getter
public class CropCycle extends AbstractEntity {

    protected static final String TABLE_NAME = "crop_cycle";

    @Column(name = "user_id", updatable = false, insertable = false)
    private Integer userId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "cropCycle")
    private CropCycleControl control;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(max = 50)
    @Column(name = "crop", nullable = false, length = 50)
    private String crop;

    @Column(name = "planted_area", precision = 10, scale = 2)
    private BigDecimal plantedArea;

    @Column(name = "measurement_unit", length = 15)
    private MeasurementUnit measurementUnit;

    @Column(name = "plant_count")
    private Integer plantCount;

    @Column(name = "planned_budget", precision = 15, scale = 2)
    private BigDecimal plannedBudget;

    @Column(name = "target_yield", precision = 15, scale = 2)
    private BigDecimal targetYield;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", nullable = false, columnDefinition = "crop_cycle_status")
    private CropCycleStatus status = CropCycleStatus.PLANNING;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}
