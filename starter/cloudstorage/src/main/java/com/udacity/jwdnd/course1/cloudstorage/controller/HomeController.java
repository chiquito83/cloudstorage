package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.fbb.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

  NoteService noteService;
  UserService userService;
  CredentialsService credentialsService;


  public HomeController(NoteService noteService, UserService userService, CredentialsService credentialsService) {
    this.noteService = noteService;
    this.userService = userService;
    this.credentialsService = credentialsService;
  }

  @GetMapping
  public String getHomePage(@ModelAttribute("noteForm") NoteForm noteForm,
                            @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                            Model model, Principal principal) { //todo

    User user = userService.getByUsername(principal.getName());


    model.addAttribute("notes", noteService.getNotes(user.getUserid()));
    model.addAttribute("credentialsList", credentialsService.getCredentials(user.getUserid()));




    return "home";
  }


  public String postHome(@ModelAttribute("noteForm") NoteForm noteForm,
                         @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                         Model model, Principal principal) {


    User user = userService.getByUsername(principal.getName());



    Note note = user.createNote(noteForm.getTitle(), noteForm.getDescription());


    int r = noteService.createNote(note);


    //model.addAttribute("notes", noteService.getNotes(user.getUserid()));
    //model.addAttribute("credentialsList", credentialsService.getCredentials(user.getUserid()));


    return "home";
  }





}
