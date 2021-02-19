package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class FileController {

  private FileService fileService;
  private UserService userService;

  public FileController(FileService fileService, UserService userService) {
    this.fileService = fileService;
    this.userService = userService;
  }

  @PostMapping("/file")
  public void uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile,
                         Principal principal,
                         Model model,
                         HttpServletResponse response
                         ) throws IOException {

    System.out.println("inside uploadFile method");

    User user = userService.getByUsername(principal.getName());

    System.out.println("user : " + user.getUsername());

    try {
      File file = fromMultipartFile(multipartFile, user);

      System.out.println("received file");

      fileService.addFile(file);

      System.out.println("added file to database");

    } catch (IOException e) {
      System.out.println("something went wrong");
      e.printStackTrace();
    }

    response.sendRedirect("/home");




  }

  private File fromMultipartFile(MultipartFile mpf, User user) throws IOException {
    return new File(null, mpf.getOriginalFilename(), mpf.getContentType(), mpf.getSize()+"", user.getUserid(), mpf.getBytes());
  }
}
