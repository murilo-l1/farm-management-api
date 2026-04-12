package com.dev.farmmanager.domain.payload.stakeholder;

import com.dev.farmmanager.domain.enumeration.StakeholderType;
import com.dev.farmmanager.exception.handler.message.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public record StakeholderPayload(
        @NotBlank(message = ErrorMessage.REQUIRED_NAME)
        @Size(max = 255, message = ErrorMessage.INVALID_NAME_LENGTH)
        String name,

        @CPF(message = ErrorMessage.INVALID_CPF)
        String cpf,

        @CNPJ(message = ErrorMessage.INVALID_CNPJ)
        String cnpj,

        StakeholderType type,

        @Size(max = 20, message = ErrorMessage.INVALID_PHONE_STAKEHOLDER_LENGTH)
        String phone
) {}
