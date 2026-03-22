package com.dev.farmmanager.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
@Table(name = TransactionItem.TABLE_NAME)
public class TransactionItem {

    protected static final String TABLE_NAME = "transaction_item";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected Integer id;

    @Column(name = "transaction_id", updatable = false, insertable = false)
    private Integer transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "item_id", updatable = false, insertable = false)
    private Integer itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @PositiveOrZero
    @Column(name = "quantity", precision = 15, scale = 2)
    private BigDecimal quantity;

    @PositiveOrZero
    @Column(name = "unit_price", precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @PositiveOrZero
    @Column(name = "total_price", precision = 15, scale = 2)
    private BigDecimal totalPrice;

}
