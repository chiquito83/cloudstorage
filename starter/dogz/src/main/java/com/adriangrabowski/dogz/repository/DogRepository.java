package com.adriangrabowski.dogz.repository;

import com.adriangrabowski.dogz.model.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
