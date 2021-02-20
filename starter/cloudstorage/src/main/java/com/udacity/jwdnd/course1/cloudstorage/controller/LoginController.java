package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

  public LoginController() {
  }

  @GetMapping("/login")
  public String getLoginPage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {


    return "login";
  }

  @PostMapping("/login")
  public String postLoginPage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {


    return "login";
  }


}
