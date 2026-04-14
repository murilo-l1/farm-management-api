package com.dev.farmmanager.domain.dto.transaction;

import java.util.List;

public record TransactionPageDto(
        TransactionSummaryDto summary,
        List<TransactionRowDto> transactions
) {}
