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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile,
                         Principal principal,
                         Model model,
                         HttpServletResponse response,
                         RedirectAttributes redirectAttributes
                         )  {


    User user = userService.getByUsername(principal.getName());

    if (fileNameAlreadyExists(user, multipartFile.getOriginalFilename())) {
      redirectAttributes.addFlashAttribute("error", Message.FILE_ALREADY_EXISTS.getText());
      return "redirect:/home";
    }


    try {
      File file = fromMultipartFile(multipartFile, user);


      int r = fileService.addFile(file);

      if (r > 0) {
        redirectAttributes.addFlashAttribute("success", Message.FILE_UPLOADED.getText());
      }
      else {
        redirectAttributes.addFlashAttribute("error", Message.FILE_ERROR_UPLOADING.getText());
      }


    } catch (IOException e) {

      redirectAttributes.addFlashAttribute("error", Message.FILE_ERROR_UPLOADING.getText());


    }

    return "redirect:/home";


  }

  private File fromMultipartFile(MultipartFile mpf, User user) throws IOException {
    return new File(null, mpf.getOriginalFilename(), mpf.getContentType(), mpf.getSize()+"", user.getUserid(), mpf.getBytes());
  }

  private boolean fileNameAlreadyExists(User user, String fileName) {
    return fileService.getFiles(user.getUserid()).stream()
            .anyMatch(f -> f.getFileName().equals(fileName));
  }
}
