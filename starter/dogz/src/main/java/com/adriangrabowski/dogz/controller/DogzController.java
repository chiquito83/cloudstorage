package com.adriangrabowski.dogz.controller;

import com.adriangrabowski.dogz.service.DogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogzController {

  private DogService dogService;

  public DogzController(DogService dogService) {
    this.dogService = dogService;
  }

  @GetMapping("/names")
  public ResponseEntity<List<String>> getDogNames() {

    List<String> names = dogService.retrieveDogNames();

    if (names != null && names.size() > 0) {
      return new ResponseEntity<List<String>>(names, HttpStatus.OK);
    }

    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }
}
