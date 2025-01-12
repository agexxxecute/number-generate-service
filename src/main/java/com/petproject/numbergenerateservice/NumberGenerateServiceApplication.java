package com.petproject.numbergenerateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс микросервиса для генерации номера заказа.
 * Позволяет сгенерировать номер заказа по заданному алгоритму и сохранить его.
 *
 * @author Egor Nazarev
 */
@SpringBootApplication
public class NumberGenerateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberGenerateServiceApplication.class, args);
    }

}
