package com.petproject.numbergenerateservice.controller;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.exception.handler.ErrorResponseDto;
import com.petproject.numbergenerateservice.service.GeneratedNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbers")
@RequiredArgsConstructor
@Tag(name = "Генерация номера", description = "Предоставляет конечные точки для генерации номеров")
public class GeneratedNumberController {

    private final GeneratedNumberService generatedNumberService;

    @Operation(description = "Позволяет сгенерировать номер заказа")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Создание заказа произошло успешно"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "507", description = "Достигнуто ограничение количества номеров за день", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GeneratedNumberDto generateNumber() {
        return generatedNumberService.generateNumber();
    }
}
