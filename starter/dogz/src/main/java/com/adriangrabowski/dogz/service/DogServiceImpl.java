package com.adriangrabowski.dogz.service;

import com.adriangrabowski.dogz.repository.DogRepository;
import com.adriangrabowski.dogz.service.DogService;
import com.adriangrabowski.dogz.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DogServiceImpl implements DogService {


  private DogRepository dogRepository;

  public DogServiceImpl(DogRepository dogRepository) {
    this.dogRepository = dogRepository;
  }

  @Override
  public String retrieveDogBreed(Dog dog) {

    return dogRepository.findById(dog.getId()).get().getBreed();


  }

  @Override
  public String retrieveDogBreedById(Long id) {
    return dogRepository.findById(id).get().getBreed();
  }

  @Override
  public List<String> retrieveDogNames() {
    return StreamSupport.stream(dogRepository.findAll().spliterator(), false)
            .map(d -> d.getName()).collect(Collectors.toList());
  }
}
