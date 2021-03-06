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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String postNote(@ModelAttribute("noteForm") NoteForm noteForm,
                         @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                         Model model, Principal principal,
                         HttpServletResponse response,
                         RedirectAttributes redirectAttributes
  )  {

    User user = userService.getByUsername(principal.getName());

    Note existingNote = noteService.getNote(noteForm.getId());


    if (existingNote != null) {

      if (user.getUserid().equals(existingNote.getUserid())) {
        Note updatedNote = new Note(noteForm.getId(), noteForm.getTitle(), noteForm.getDescription(), existingNote.getUserid());
        int rows = noteService.update(updatedNote);

        if (rows > 0) {
          redirectAttributes.addFlashAttribute("success", Message.NOTE_UPDATED.getText());
        } else {
          redirectAttributes.addFlashAttribute("error", Message.NOTE_ERROR.getText());
        }
      }

    } else {

      Note note = user.createNote(noteForm.getTitle(), noteForm.getDescription());

      int r = noteService.createNote(note);

      if (r == 1) {
        redirectAttributes.addFlashAttribute("success", Message.NOTE_ADDED.getText());
      } else {
        redirectAttributes.addFlashAttribute("error", Message.NOTE_ERROR.getText());
      }

    }


    return "redirect:/home";


  }



  @GetMapping("/deleteNote/{id}")
  public String deleteNote(@ModelAttribute("noteForm") NoteForm noteForm,
                         Model model, Principal principal,
                         @PathVariable("id") Long noteId,
                         HttpServletResponse response,
                         RedirectAttributes redirectAttributes
  ) throws IOException {


    User user = userService.getByUsername(principal.getName());

    Note note = noteService.getNote(noteId);

    if (note.getUserid().equals(user.getUserid())) {

      int r = noteService.deleteNote(note.getNoteid());

      if (r > 0) {
        redirectAttributes.addFlashAttribute("success", Message.NOTE_DELETED);
      }
      else {
        redirectAttributes.addFlashAttribute("error", Message.NOTE_ERROR);
      }

    }
    else {
      redirectAttributes.addFlashAttribute("error", Message.NOTE_ERROR);
    }



    return "redirect:/home";

  }


}
