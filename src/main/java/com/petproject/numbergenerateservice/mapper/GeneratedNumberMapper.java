package com.petproject.numbergenerateservice.mapper;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.model.GeneratedNumber;
import org.mapstruct.Mapper;

/**
 * Интерфейс для преобразования объектов GeneratedNumber в Dto и обратно
 *
 * @author Egor Nazarev
 */
@Mapper(componentModel = "spring")
public interface GeneratedNumberMapper {

    /**
     * Метод для преобразования GeneratedNumberDto в GeneratedNumber
     * @param generatedNumberDto dto с номером заказа
     * @return объект GeneratedNumber
     */
    GeneratedNumber toEntity(GeneratedNumberDto generatedNumberDto);
}
