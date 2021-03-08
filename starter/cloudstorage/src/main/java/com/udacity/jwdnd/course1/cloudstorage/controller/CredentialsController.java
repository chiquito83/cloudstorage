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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

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
  public String postCredentials(@ModelAttribute("noteForm") NoteForm noteForm,
                              @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                              Model model, Principal principal,
                              HttpServletResponse response,
                                RedirectAttributes redirectAttributes
                              )  {


    User user = userService.getByUsername(principal.getName());

    Credentials existingCredentials = credentialsService.getById(credentialsForm.getId());


    if (existingCredentials != null) {


      if (existingCredentials.getUserid().equals(user.getUserid())) {
        Credentials updated = new Credentials(existingCredentials.getCredentialid(),
                credentialsForm.getUrl(), credentialsForm.getUsername(),
                credentialsForm.getPassword(), existingCredentials.getKey(), user.getUserid()
                 );

        if (urlAlreadyExists(user, credentialsForm.getUrl(), existingCredentials)) {
          redirectAttributes.addFlashAttribute("error", Message.CREDENTIALS_ALREADY_EXIST.getText());
          return "redirect:/home";
        }

        int r = credentialsService.delete(credentialsForm.getId());
        int rr = credentialsService.createCredentials(updated);

        if (r + rr > 1) {
          redirectAttributes.addFlashAttribute("success", Message.CREDENTIALS_UPDATED.getText());
        }
        else {
          redirectAttributes.addFlashAttribute("error", Message.CREDENTIALS_ERROR.getText());
        }

      }
      else {
        redirectAttributes.addFlashAttribute("error", Message.CREDENTIALS_ERROR.getText());
      }

    }

    else {

      Credentials credentials = user.createCredentials(credentialsForm.getUrl(),
              credentialsForm.getUsername(), credentialsForm.getPassword());

      if (urlAlreadyExists(user, credentialsForm.getUrl(), existingCredentials)) {
        redirectAttributes.addFlashAttribute("error", Message.CREDENTIALS_ALREADY_EXIST.getText());
        return "redirect:/home";
      }

      int r = credentialsService.createCredentials(credentials);

      if (r > 0) {
        redirectAttributes.addFlashAttribute("success", Message.CREDENTIALS_ADDED.getText());
      }
      else {
        redirectAttributes.addFlashAttribute("error", Message.CREDENTIALS_ERROR.getText());
      }


    }




    return "redirect:/home";
  }

  @GetMapping("deleteCredentials/{id}")
  public String deleteCredentials(Model model,
                                  Principal principal,
                                  HttpServletResponse response,
                                  @PathVariable("id") Long credentialsId,
                                  RedirectAttributes redirectAttributes) throws IOException {

    User user = userService.getByUsername(principal.getName());

    Credentials credentials = credentialsService.getById(credentialsId);

    if (credentials.getUserid().equals(user.getUserid())) {
      int r = credentialsService.delete(credentialsId);

      if (r > 0) {
        redirectAttributes.addFlashAttribute("success", Message.CREDENTIALS_DELETED.getText());
      }
      else {
        redirectAttributes.addFlashAttribute("error", Message.CREDENTIALS_ERROR.getText());
      }
    }
    else {
      redirectAttributes.addFlashAttribute("error", Message.CREDENTIALS_ERROR.getText());
    }

    return "redirect:/home";

  }

  private boolean urlAlreadyExists(User user, String url, Credentials existingCredentials) {
    List<Credentials> credentialsList = credentialsService.getCredentials(user.getUserid());

    boolean b = credentialsList.stream().anyMatch(c -> c.getUrl().equals(url));

    if (null == existingCredentials) {
      return b;
    }
    else {
      return !credentialsList.stream().filter(c -> c.getUrl().equals(url))
              .allMatch(c -> c.getCredentialid().equals(existingCredentials.getCredentialid()));
    }

  }

}


