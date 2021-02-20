package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.SignupForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/signup")
public class SignupController {

  private UserService userService;

  public SignupController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String getSignupPage(@ModelAttribute("signupForm") SignupForm signupForm, Model model) {

    return "signup";

  }

  @PostMapping
  public String postSignupPage(@ModelAttribute("signupForm") SignupForm signupForm,
                               Model model,
                               HttpServletResponse response
                               ) throws IOException {

    System.out.println(signupForm);

    String error = null;

    boolean noErrors = true;

    if (!userService.isUsernameAvailable(signupForm.getUsername())) {
      error = "The username already exists";
      noErrors = false;
    }

    if (noErrors) {
      User user = new User(null, signupForm.getUsername(),
              signupForm.getFirstName(), signupForm.getLastName(), null,  signupForm.getPassword());

      int rowsAdded = userService.createUser(user);

      if (rowsAdded < 1) {
        error = "There was an error signing you up";
        noErrors = false;
      }
    }

    if (noErrors) {
      model.addAttribute("signupSuccess", true);
      model.addAttribute("successMessage", "Registration successful. Please login.");
      response.sendRedirect("/login");
    }
    else {
      model.addAttribute("errorMessage", error);
      model.addAttribute("signupSuccess", false);
    }



    return "signup";
  }
}
