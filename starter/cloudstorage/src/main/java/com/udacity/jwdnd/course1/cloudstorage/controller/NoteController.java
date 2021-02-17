package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.fbb.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class NoteController {

  private NoteService noteService;
  private UserService userService;
  private CredentialsService credentialsService;

  public NoteController(NoteService noteService, UserService userService, CredentialsService credentialsService) {
    this.noteService = noteService;
    this.userService = userService;
    this.credentialsService = credentialsService;
  }

  @PostMapping("/note")
  public String postNote(@ModelAttribute("noteForm") NoteForm noteForm,
                         @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                         Model model, Principal principal) {

    User user = userService.getByUsername(principal.getName());
    Note note = user.createNote(noteForm.getTitle(), noteForm.getDescription());

    int r = noteService.createNote(note);

    model.addAttribute("notes", noteService.getNotes(user.getUserid()));
    model.addAttribute("credentialsList", credentialsService.getCredentials(user.getUserid()));

    return "home";
  }

  @GetMapping("/deleteNote/{id}")
  public String deleteNote(@ModelAttribute("noteForm") NoteForm noteForm,
                           @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                           Model model, Principal principal,
                           @PathVariable("id") Long noteId
                           ) {

    System.out.println("trying to delete a note id: " + noteId );

    User user = userService.getByUsername(principal.getName());

    Note note = noteService.getNote(noteId);

    if (note.getUserid().equals(user.getUserid())) {

      int r = noteService.deleteNote(note.getNoteid());
      System.out.println("deleted " + r + " rows");

    }



    model.addAttribute("notes", noteService.getNotes(user.getUserid()));
    model.addAttribute("credentialsList", credentialsService.getCredentials(user.getUserid()));


    return "home";

  }


}
