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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

  private CredentialsService credentialsService;
  private UserService userService;
  private NoteService noteService;


  public CredentialsController(CredentialsService credentialsService, UserService userService, NoteService noteService) {
    this.credentialsService = credentialsService;
    this.userService = userService;
    this.noteService = noteService;
  }

  @PostMapping
  public String postCredentials(@ModelAttribute("noteForm") NoteForm noteForm,
          @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                                Model model, Principal principal ) {

    System.out.println("post credentials called");

    User user = userService.getByUsername(principal.getName());

    Credentials credentials = user.createCredentials(credentialsForm.getUrl(),
            credentialsForm.getUsername(), credentialsForm.getPassword());

    int r = credentialsService.createCredentials(credentials);

    System.out.println("added " + r + " credential");

    model.addAttribute("credentialsList", credentialsService.getCredentials(user.getUserid()));
    model.addAttribute("notes", noteService.getNotes(user.getUserid()));

    return "home";
  }
}


