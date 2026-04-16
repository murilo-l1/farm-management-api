package com.dev.farmmanager.exception.handler;

public class CropCycleControlNotFoundException extends NotFoundException {
    public CropCycleControlNotFoundException() {
        super("Controle de Ciclo Safra não encontrado");
    }
}
