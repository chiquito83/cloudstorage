package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.fbb.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class CredentialsController {

  private CredentialsService credentialsService;
  private UserService userService;
  private NoteService noteService;


  public CredentialsController(CredentialsService credentialsService, UserService userService, NoteService noteService) {
    this.credentialsService = credentialsService;
    this.userService = userService;
    this.noteService = noteService;
  }

  @PostMapping("/credentials")
  public void postCredentials(@ModelAttribute("noteForm") NoteForm noteForm,
                              @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                              Model model, Principal principal,
                              HttpServletResponse response
                              ) throws IOException {

    System.out.println("post credentials called");

    User user = userService.getByUsername(principal.getName());

    Credentials credentials = user.createCredentials(credentialsForm.getUrl(),
            credentialsForm.getUsername(), credentialsForm.getPassword());

    int r = credentialsService.createCredentials(credentials);

    System.out.println("added " + r + " credential");


    response.sendRedirect("/home");
  }

  @GetMapping("deleteCredentials/{id}")
  public void deleteCredentials(Model model,
                                Principal principal,
                                HttpServletResponse response,
                                @PathVariable("id") Long credentialsId) throws IOException {

    User user = userService.getByUsername(principal.getName());

    Credentials credentials = credentialsService.getById(credentialsId);

    if (credentials.getUserid().equals(user.getUserid())) {
      credentialsService.delete(credentialsId);
    }

    response.sendRedirect("/home");

  }

}


