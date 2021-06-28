package com.ohmynone.auth.mapper;

import com.ohmynone.auth.dto.UserDto;
import com.ohmynone.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "username", source = "email")
    User map(UserDto dto);

    @Mapping(target = "uid", ignore = true)
    @Mapping(target = "username", source = "email")
    User map(UserDto dto, @MappingTarget User origin);

    @Mapping(target = "password", ignore = true)
    UserDto map(User entity);
}
