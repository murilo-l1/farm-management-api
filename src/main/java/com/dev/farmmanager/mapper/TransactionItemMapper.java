package com.dev.farmmanager.mapper;

import com.dev.farmmanager.domain.dto.transaction.TransactionItemDto;
import com.dev.farmmanager.domain.entity.TransactionItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionItemMapper {

    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "item.name", target = "itemName")
    TransactionItemDto toDto(TransactionItem transactionItem);
}
