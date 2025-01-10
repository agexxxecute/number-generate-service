package com.petproject.numbergenerateservice.mapper;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.model.GeneratedNumber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneratedNumberMapper {
    GeneratedNumber toEntity(GeneratedNumberDto generatedNumberDto);
}
