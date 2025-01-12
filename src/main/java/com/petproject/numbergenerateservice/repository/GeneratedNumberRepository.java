package com.petproject.numbergenerateservice.repository;

import com.petproject.numbergenerateservice.model.GeneratedNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с номерами заказа в базе данных
 *
 * @author Egor Nazarev
 */
@Repository
public interface GeneratedNumberRepository extends MongoRepository<GeneratedNumber, String> {
}
