package com.petproject.numbergenerateservice.service.impl;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.exception.InsufficientStorageException;
import com.petproject.numbergenerateservice.mapper.GeneratedNumberMapper;
import com.petproject.numbergenerateservice.model.GeneratedNumber;
import com.petproject.numbergenerateservice.repository.GeneratedNumberRepository;
import com.petproject.numbergenerateservice.service.GeneratedNumberService;
import com.petproject.numbergenerateservice.util.ExceptionMessage;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Реализация интерфейса генерации номера заказа.
 *
 * @author Egor Nazarev
 */
@Service
@RequiredArgsConstructor
public class GeneratedNumberServiceImpl implements GeneratedNumberService {

    private final GeneratedNumberRepository generatedNumberRepository;
    private final GeneratedNumberMapper generatedNumberMapper;

    private Set<Integer> todayNumbers = new HashSet<>();
    private final Integer MAX_NUMBERS = 1_000_000;

    /**
     * Реализация метода для генерации номера заказа.
     * @return Dto со сгенерированным номером заказа.
     */
    @Override
    public GeneratedNumberDto generateNumber() {
        GeneratedNumberDto generatedNumberDto = new GeneratedNumberDto(generateRandomNumber() + LocalDate.now().toString().replace("-",""));
        GeneratedNumber generatedNumber = generatedNumberMapper.toEntity(generatedNumberDto);
        generatedNumberRepository.save(generatedNumber);
        return generatedNumberDto;
    }

    /**
     * Метод генерирует уникальное случайное число от 0 до 999 999.
     * @return сгенерированное число в формате строки длинной в 6 символов.
     */
    private String generateRandomNumber(){
        if(todayNumbers.size() < MAX_NUMBERS) {
            Random random = new Random();
            Integer number = random.nextInt(MAX_NUMBERS);
            while (todayNumbers.contains(number)) {
                number = random.nextInt(MAX_NUMBERS);
            }
            todayNumbers.add(number);
            return String.format("%06d", number);
        } else {
            throw new InsufficientStorageException(ExceptionMessage.INSUFFICIENT_STORAGE_ERROR.getDescription());
        }
    }

    /**
     * Метод, очищающий список сгенерированных чисел ежедневно.
     */
    @Scheduled(cron = "@daily")
    private void clearNumbersDaily(){
        todayNumbers.clear();
    }
}
