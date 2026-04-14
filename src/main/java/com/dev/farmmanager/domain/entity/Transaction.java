package com.dev.farmmanager.domain.entity;

import com.dev.farmmanager.domain.enumeration.PaymentMethod;
import com.dev.farmmanager.domain.enumeration.TransactionStatus;
import com.dev.farmmanager.domain.enumeration.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;

import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Transaction.TABLE_NAME)
@Getter
@Setter
public class Transaction extends AbstractEntity {

    protected static final String TABLE_NAME = "transaction";

    @Column(name = "user_id", updatable = false, insertable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "crop_cycle_id", updatable = false, insertable = false)
    private Integer cropCycleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_cycle_id")
    private CropCycle cropCycle;

    @Column(name = "stakeholder_id", updatable = false, insertable = false)
    private Integer stakeholderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stakeholder_id")
    private Stakeholder stakeholder;

    @Column(name = "category_id", updatable = false, insertable = false)
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "type", nullable = false, columnDefinition = "transaction_type")
    private TransactionType type;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @PositiveOrZero
    @Column(name = "total_value", precision = 15, scale = 2)
    private BigDecimal totalValue;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Enumerated(value = EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", columnDefinition = "transaction_status")
    private TransactionStatus status = TransactionStatus.PENDING;

    @Enumerated(value = EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "payment_method", columnDefinition = "payment_method")
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private List<TransactionItem> items = new ArrayList<>();

}
