package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {

  private NoteService noteService;
  private UserService userService;

  public NoteController(NoteService noteService, UserService userService) {
    this.noteService = noteService;
    this.userService = userService;
  }

  @PostMapping
  public String postNote(@ModelAttribute("noteForm") NoteForm noteForm, Model model, Principal principal) {

    User user = userService.getByUsername(principal.getName());
    Note note = user.createNote(noteForm.getTitle(), noteForm.getDescription());

    int r = noteService.createNote(note);

    model.addAttribute("notes", noteService.getNotes(user.getUserid()));

    return "home";
  }
}
