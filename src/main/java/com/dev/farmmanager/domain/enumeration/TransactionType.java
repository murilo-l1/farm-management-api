package com.dev.farmmanager.domain.enumeration;

import lombok.Getter;

import java.util.List;

@Getter
public enum TransactionType {
    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private final String value;

    private final static List<TransactionType> values = List.of(TransactionType.values());

    TransactionType(String value) {
        this.value = value;
    }
}
