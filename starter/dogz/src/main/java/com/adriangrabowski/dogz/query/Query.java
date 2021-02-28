package com.adriangrabowski.dogz.query;

import com.adriangrabowski.dogz.model.Dog;
import com.adriangrabowski.dogz.repository.DogRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {

  private final DogRepository dogRepository;

  public Query(DogRepository dogRepository) {
    this.dogRepository = dogRepository;
  }

  public Iterable<Dog> findAllDogNames() {
    return dogRepository.findAll();
  }
}
