package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

  public LoginController() {
  }

  @GetMapping("/login")
  public String getLoginPage(@ModelAttribute("loginForm") LoginForm loginForm,
                             Model model,
                             HttpServletRequest request,
                             RedirectAttributes redirectAttributes
                             ) {




    if (request.getParameterMap().containsKey("error")) {
      model.addAttribute("error", Message.LOGIN_ERROR.getText());

    }

    if (request.getParameterMap().containsKey("logout")) {
      model.addAttribute("success", Message.LOGGED_OUT.getText());

    }


    return "login";
  }

  @PostMapping("/login")
  public String postLoginPage(@ModelAttribute("loginForm") LoginForm loginForm,
                              Model model) {


    return "login";
  }


}
