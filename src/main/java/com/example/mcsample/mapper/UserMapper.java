package com.example.mcsample.mapper;

import com.example.mcsample.dto.UserDTO;
import com.example.mcsample.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "uid", ignore = true)
    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    @Mapping(target = "uid", ignore = true)
    void updateEntity(UserDTO userDTO, @MappingTarget User user);

}