package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

  public SignupController() {
  }

  @GetMapping
  public String getSignupPage(@ModelAttribute("signupForm") SignupForm signupForm, Model model) {

    return "signup";

  }

  @PostMapping
  public String postSignupPage(@ModelAttribute("signupForm") SignupForm signupForm, Model model) {

    return "signup";
  }
}
