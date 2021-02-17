package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/credentials")
public class CredentialsController {

  private CredentialsService credentialsService;
  private UserService userService;

  public CredentialsController(CredentialsService credentialsService, UserService userService) {
    this.credentialsService = credentialsService;
    this.userService = userService;
  }

  @PostMapping
  public String postCredentials(@ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                                Model model, Principal principal ) {

    User user = userService.getByUsername(principal.getName());

    Credentials credentials = user.createCredentials(credentialsForm.getUrl(),
            credentialsForm.getUsername(), credentialsForm.getPassword());

    int r = credentialsService.createCredentials(credentials);

    System.out.println("added " + r + " credential");



    return "home";
  }
}


