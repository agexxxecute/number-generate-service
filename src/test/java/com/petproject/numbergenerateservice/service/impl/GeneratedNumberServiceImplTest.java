package com.petproject.numbergenerateservice.service.impl;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.exception.InsufficientStorageException;
import com.petproject.numbergenerateservice.mapper.GeneratedNumberMapper;
import com.petproject.numbergenerateservice.repository.GeneratedNumberRepository;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Set;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GeneratedNumberServiceImplTest {
    @Mock
    private GeneratedNumberRepository generatedNumberRepository;

    @Mock
    private GeneratedNumberMapper generatedNumberMapper;

    @InjectMocks
    private GeneratedNumberServiceImpl generatedNumberService;

    private Integer VALID_GENERATED_NUMBER_LENGTH = 14;
    private Integer MAX_NUMBER = 1_000_000;

    @Test
    @DisplayName("Should generate order number successfully")
    void generateNumberSuccessfully(){
        GeneratedNumberDto generatedNumberDtoResult = generatedNumberService.generateNumber();
        String result = generatedNumberDtoResult.orderNumber();

        Assertions.assertEquals(VALID_GENERATED_NUMBER_LENGTH, result.length());
        Assertions.assertEquals(LocalDate.now().toString().replace("-", ""), result.substring(6));
    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw Insufficient Storage Exception")
    void generateNumberInsufficientStorageException(){
        Field todayNumbersField = GeneratedNumberServiceImpl.class.getDeclaredField("todayNumbers");
        todayNumbersField.setAccessible(true);
        Set<Integer> todayNumbers = (Set<Integer>) todayNumbersField.get(generatedNumberService);

        int maxNumber = MAX_NUMBER;
        for (int i = 0; i < maxNumber; i++) {
            todayNumbers.add(i);
        }

        Assertions.assertThrows(InsufficientStorageException.class, () -> generatedNumberService.generateNumber());
    }

}