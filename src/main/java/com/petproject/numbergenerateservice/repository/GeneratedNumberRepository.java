package com.petproject.numbergenerateservice.repository;

import com.petproject.numbergenerateservice.dto.GeneratedNumberDto;
import com.petproject.numbergenerateservice.model.GeneratedNumber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeneratedNumberRepository extends MongoRepository<GeneratedNumber, String> {
}
