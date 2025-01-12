package com.petproject.numbergenerateservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Сущность, описывающая номер заказа
 *
 * @author Egor Nazarev
 */
@Data
@Document (collection = "order_numbers")
public class GeneratedNumber {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field(value = "orderNumber")
    private String orderNumber;

}
