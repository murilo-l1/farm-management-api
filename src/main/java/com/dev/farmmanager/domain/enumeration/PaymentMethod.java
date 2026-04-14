package com.dev.farmmanager.domain.enumeration;

import lombok.Getter;

import java.util.List;

@Getter
public enum PaymentMethod {
    CARD("CARD"),
    CASH("CASH"),
    PIX("PIX"),
    CHECK("CHECK"),
    BOLETO("BOLETO");

    private final String value;

    private final static List<PaymentMethod> values = List.of(PaymentMethod.values());

    PaymentMethod(String value) {
        this.value = value;
    }
}
