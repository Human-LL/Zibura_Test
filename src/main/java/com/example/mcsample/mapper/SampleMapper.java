package com.example.mcsample.mapper;

import com.example.mcsample.dto.SampleDTO;
import com.example.mcsample.entity.Samples;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SampleMapper {
    @Mapping(target = "uid", ignore = true)
Samples toEntity(SampleDTO sampleDTO);

    SampleDTO toDTO(Samples samples);
}