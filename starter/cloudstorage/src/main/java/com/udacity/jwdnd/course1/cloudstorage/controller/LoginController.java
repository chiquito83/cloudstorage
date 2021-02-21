package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.fbb.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class LoginController {

  public LoginController() {
  }

  @GetMapping("/login")
  public String getLoginPage(@ModelAttribute("loginForm") LoginForm loginForm,
                             Model model,
                             HttpServletRequest request) {


    boolean redirectedAfterSignup = Arrays.stream(request
            .getCookies()).anyMatch(c -> (c.getName()
            .equals("redirectMessage") && c.getValue().equals("RS")));



    boolean redirectedAfterLogout = false;
    boolean invalidUsername = false;

    if (request.getParameterMap().containsKey("error")) {
      invalidUsername = true;
    }

    if (request.getParameterMap().containsKey("logout")) {
      redirectedAfterLogout = true;
    }

    model.addAttribute("signedUp", redirectedAfterSignup);
    model.addAttribute("invalidUsername", invalidUsername);
    model.addAttribute("loggedOut", redirectedAfterLogout);




    return "login";
  }

  @PostMapping("/login")
  public String postLoginPage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {


    return "login";
  }


}
