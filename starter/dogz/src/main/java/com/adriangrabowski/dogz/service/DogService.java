package com.adriangrabowski.dogz.service;

import com.adriangrabowski.dogz.model.Dog;


import java.util.List;


public interface DogService {

  public String retrieveDogBreed(Dog dog);
  public String retrieveDogBreedById(Long id);
  public List<String> retrieveDogNames();
}
