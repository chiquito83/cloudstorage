package com.adriangrabowski.dogz.mutator;

import com.adriangrabowski.dogz.model.Dog;
import com.adriangrabowski.dogz.repository.DogRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import java.util.Optional;

public class Mutator implements GraphQLMutationResolver {

  private final DogRepository dogRepository;

  public Mutator(DogRepository dogRepository) {
    this.dogRepository = dogRepository;
  }

  public boolean deleteDogBreed(String breed) {
    Optional<Dog> dog
  }
}
