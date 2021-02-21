package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
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

  @GetMapping("/file/{id}")
  public ResponseEntity viewFile(@PathVariable("id") Long id,
                       Principal principal,
                       Model model,
                       HttpServletResponse response
                       ) throws IOException {

    User user = userService.getByUsername(principal.getName());

    File file = fileService.readFile(id);

    System.out.println(file);

    if (file.getUserid().equals(user.getUserid())) {

      String fileName = file.getFileName();
      MediaType mediaType = MediaType.parseMediaType(file.getContentType());

      String contentDisposition = "Content-Disposition: attachment; filename=\"" + fileName + "\"";

      return ResponseEntity.ok()
              .contentType(mediaType)
              .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
              .body(file.getFileData());

    }


    response.sendRedirect("/home");

    return null;

  }

  @GetMapping("deleteFile/{id}")
  public void deleteFile(@PathVariable("id") Long id,
                         Principal principal,
                         Model model,
                         HttpServletResponse response
                         ) throws IOException {

    User user = userService.getByUsername(principal.getName());

    File file = fileService.readFile(id);

    if (file.getUserid().equals(user.getUserid())) {
      fileService.deleteFile(file.getFileid());
    }

    response.sendRedirect("/home");

  }

  @PostMapping("/file")
  public void uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile,
                         Principal principal,
                         Model model,
                         HttpServletResponse response
                         ) throws IOException {


    User user = userService.getByUsername(principal.getName());


    try {
      File file = fromMultipartFile(multipartFile, user);


      fileService.addFile(file);


    } catch (IOException e) {

      Cookie cookie = new Cookie("redirectMessage", "FILE_ERROR");
      cookie.setMaxAge(5);
      response.addCookie(cookie);
    }

    response.sendRedirect("/home");


  }

  private File fromMultipartFile(MultipartFile mpf, User user) throws IOException {
    return new File(null, mpf.getOriginalFilename(), mpf.getContentType(), mpf.getSize()+"", user.getUserid(), mpf.getBytes());
  }
}
