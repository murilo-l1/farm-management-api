package com.dev.farmmanager.mapper;

import com.dev.farmmanager.domain.dto.transaction.TransactionDto;
import com.dev.farmmanager.domain.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TransactionItemMapper.class})
public interface TransactionMapper {

    @Mapping(source = "cropCycle.id", target = "cropCycleId")
    @Mapping(source = "cropCycle.name", target = "cropCycleName")
    @Mapping(source = "stakeholder.id", target = "stakeholderId")
    @Mapping(source = "stakeholder.name", target = "stakeholderName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    TransactionDto toDto(Transaction transaction);
}
