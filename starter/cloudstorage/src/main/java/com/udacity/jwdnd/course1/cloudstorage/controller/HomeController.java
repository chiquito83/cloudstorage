package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
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


  public HomeController(NoteService noteService, UserService userService) {
    this.noteService = noteService;
    this.userService = userService;
  }

  @GetMapping
  public String getHomePage(@ModelAttribute("noteForm") NoteForm noteForm, Model model, Principal principal) { //todo

    User user = userService.getByUsername(principal.getName());

    model.addAttribute("notes", noteService.getNotes(user.getUserid()));




    return "home";
  }

  @PostMapping
  public String postNote(@ModelAttribute("noteForm") NoteForm noteForm, Model model, Principal principal) {


    User user = userService.getByUsername(principal.getName());

    Note note = user.createNote(noteForm.getTitle(), noteForm.getDescription());

    int r = noteService.createNote(note);

    System.out.println(r + " rows added");

    model.addAttribute("notes", noteService.getNotes(user.getUserid()));


    return "home";
  }





}
