package com.dev.farmmanager.domain.enumeration;

import lombok.Getter;

import java.util.List;

@Getter
public enum TransactionStatus {
    PENDING("PENDING"),
    FINISHED("FINISHED"),
    CANCELLED("CANCELLED");

    private final String value;

    private final static List<TransactionStatus> values = List.of(TransactionStatus.values());

    TransactionStatus(String value) {
        this.value = value;
    }
}
