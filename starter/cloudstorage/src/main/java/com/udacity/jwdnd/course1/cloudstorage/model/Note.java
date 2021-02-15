package com.udacity.jwdnd.course1.cloudstorage.model;


public class Note {

  private String title;
  private String description;

  public Note(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "Note{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
