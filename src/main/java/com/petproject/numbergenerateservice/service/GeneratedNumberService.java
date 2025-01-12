package com.petproject.numbergenerateservice.service;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;

/**
 * Интерфейс, описывающий логику генерации номера заказа.
 *
 * @author Egor Nazarev
 */
public interface GeneratedNumberService {
    /**
     * Метод для генерации номера заказа.
     * @return Dto со сгенерированным номером заказа
     */
    GeneratedNumberDto generateNumber();
}
