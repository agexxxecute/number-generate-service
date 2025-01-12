package com.petproject.numbergenerateservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.exception.InsufficientStorageException;
import com.petproject.numbergenerateservice.exception.InternalServerErrorException;
import com.petproject.numbergenerateservice.service.GeneratedNumberService;
import com.petproject.numbergenerateservice.util.ConstantUtil;
import com.petproject.numbergenerateservice.util.ExceptionMessage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = GeneratedNumberController.class)
class GeneratedNumberControllerTest {
    @MockitoBean
    private GeneratedNumberService generatedNumberService;

    @Autowired
    private MockMvc mockMvc;

    private final String GENERATE_NUMBER_URL = "/numbers";

    private static GeneratedNumberDto generatedNumberDto;

    @BeforeAll
    static void setUp(){
        generatedNumberDto = new GeneratedNumberDto(ConstantUtil.ORDER_NUMBER);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should generate order number successfully")
    void generateOrderNumberSuccessfully() {
        when(generatedNumberService.generateNumber()).thenReturn(generatedNumberDto);

        mockMvc.perform(get(GENERATE_NUMBER_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        verify(generatedNumberService).generateNumber();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void generateOrderNumberInternalServerError() {
        when(generatedNumberService.generateNumber()).thenThrow(new InternalServerErrorException(
            ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(GENERATE_NUMBER_URL)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        verify(generatedNumberService).generateNumber();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return InsufficientStorageException and satus 507")
    void generateOrderNumberInsufficientStorage() {
        when(generatedNumberService.generateNumber()).thenThrow(new InsufficientStorageException(ExceptionMessage.INSUFFICIENT_STORAGE_ERROR.getDescription()));

        mockMvc.perform(get(GENERATE_NUMBER_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInsufficientStorage())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INSUFFICIENT_STORAGE_ERROR.getDescription()))
            .andReturn();

        verify(generatedNumberService).generateNumber();
    }
}