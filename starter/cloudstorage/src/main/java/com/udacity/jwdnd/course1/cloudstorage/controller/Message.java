package com.udacity.jwdnd.course1.cloudstorage.controller;

public enum Message {



  FILE_UPLOADED("Your file was successfully uploaded."),
  FILE_ALREADY_EXISTS("File with this name already exists."),
  FILE_ERROR_UPLOADING("Something went wrong when uploading your file."),
  FILE_NOT_CHOSEN("Choose a file first."),
  FILE_DELETED("File deleted"),


  NOTE_ADDED("Your note was added."),
  NOTE_ALREADY_EXISTS("Note with this title already exists."),
  NOTE_ERROR("Something went wrong and we couldn't add your note."),
  NOTE_UPDATED("Your note was updated"),
  NOTE_DELETED("Your note was deleted"),

  CREDENTIALS_ADDED("Credentials were added."),
  CREDENTIALS_ALREADY_EXIST("You have already stored credentials for this URL."),
  CREDENTIALS_ERROR("Something went wrong, sorry."),
  CREDENTIALS_UPDATED("Your credentials were updated"),
  CREDENTIALS_DELETED("Your credentials were deleted"),

  SIGNUP_SUCCESS("You have successfully signed up. Please login."),
  SIGNUP_USER_ALREADY_EXISTS("Username already exists, please choose different username."),
  SIGNUP_OTHER_ERROR("There was error signing you up."),

  LOGIN_ERROR("Wrong username or password."),
  LOGGED_OUT("You have logged out."),
  NOT_LOGGED_IN("Please login first.");

  private String text;

  Message(String s) {
    text = s;
  }

  public String getText() {
    return text;
  }


}
