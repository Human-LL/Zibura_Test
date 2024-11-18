package com.example.mcsample.mapper;

import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    Users toEntity(UserDTO userDTO);

    UserDTO toDTO(Users users);
}
