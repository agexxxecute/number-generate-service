package com.petproject.numbergenerateservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера"),
    INSUFFICIENT_STORAGE_ERROR("Достигнуто ограничение количества номеров за день");

    private final String description;
}
