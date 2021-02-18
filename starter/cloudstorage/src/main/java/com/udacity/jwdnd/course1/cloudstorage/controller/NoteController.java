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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
  public void postNote(@ModelAttribute("noteForm") NoteForm noteForm,
                         @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                         Model model, Principal principal,
                         HttpServletResponse response
                       ) throws IOException {

    User user = userService.getByUsername(principal.getName());

    Note existingNote = noteService.getNote(noteForm.getId());


    if (existingNote != null) {

      if (user.getUserid().equals(existingNote.getUserid())) {
        Note updatedNote = new Note(noteForm.getId(), noteForm.getTitle(), noteForm.getDescription(), existingNote.getUserid());
        noteService.update(updatedNote);
      }

    }

    else {

      Note note = user.createNote(noteForm.getTitle(), noteForm.getDescription());

      int r = noteService.createNote(note);

    }




    response.sendRedirect("/home");


  }

  @GetMapping("editNote/{id}")
  public void editNote(@ModelAttribute("noteForm") NoteForm noteForm,
                       Model model,
                       Principal principal) { //todo maybe not needed

  }

  @GetMapping("/deleteNote/{id}")
  public void deleteNote(@ModelAttribute("noteForm") NoteForm noteForm,
                           Model model, Principal principal,
                           @PathVariable("id") Long noteId,
                         HttpServletResponse response
                           ) throws IOException {

    System.out.println("trying to delete a note id: " + noteId );

    User user = userService.getByUsername(principal.getName());

    Note note = noteService.getNote(noteId);

    if (note.getUserid().equals(user.getUserid())) {

      int r = noteService.deleteNote(note.getNoteid());
      System.out.println("deleted " + r + " rows");

    }

    response.sendRedirect("/home");

  }


}
