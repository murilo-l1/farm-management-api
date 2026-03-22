package com.dev.farmmanager.mapper;

import com.dev.farmmanager.domain.dto.user.FetchUserDto;
import com.dev.farmmanager.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    FetchUserDto toDto(User user);

    //User toEntity(); -- modelar isso com o payload correto

}
