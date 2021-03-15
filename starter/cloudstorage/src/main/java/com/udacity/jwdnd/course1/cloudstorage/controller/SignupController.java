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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                               HttpServletResponse response,
                               RedirectAttributes redirectAttributes
                               ) throws IOException {



    boolean noErrors = true;

    if (!userService.isUsernameAvailable(signupForm.getUsername())) {
      noErrors = false;
      redirectAttributes.addFlashAttribute("error", Message.SIGNUP_USER_ALREADY_EXISTS.getText());

    }

    if (noErrors) {
      User user = new User(null, signupForm.getUsername(),
              signupForm.getFirstName(), signupForm.getLastName(), null,  signupForm.getPassword());

      int rowsAdded = userService.createUser(user);

      if (rowsAdded < 1) {
        redirectAttributes.addFlashAttribute("error", Message.SIGNUP_OTHER_ERROR.getText());
        noErrors = false;
      }
    }

    if (noErrors) {


      redirectAttributes.addFlashAttribute("success", Message.SIGNUP_SUCCESS.getText());



      return "redirect:/login";


    }
    else {

    }



    return "redirect:/signup";


  }
}
