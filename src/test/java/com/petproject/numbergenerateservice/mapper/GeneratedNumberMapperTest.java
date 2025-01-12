package com.petproject.numbergenerateservice.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.model.GeneratedNumber;
import com.petproject.numbergenerateservice.util.ConstantUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class GeneratedNumberMapperTest {

    private GeneratedNumberMapper generatedNumberMapper;
    private GeneratedNumberDto generatedNumberDto;

    @BeforeEach
    void setUp() {
        generatedNumberMapper = Mappers.getMapper(GeneratedNumberMapper.class);

        generatedNumberDto = new GeneratedNumberDto(ConstantUtil.ORDER_NUMBER);
    }

    @Test
    @DisplayName("GeneratedNumberDto to GeneratedNumber")
    void generatedNumberToGeneratedNumber() {
        GeneratedNumber mappedGeneratedNumber = generatedNumberMapper.toEntity(generatedNumberDto);

        assertEquals(generatedNumberDto.orderNumber(), mappedGeneratedNumber.getOrderNumber());
    }


}