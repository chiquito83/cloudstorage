package com.udacity.jwdnd.course1.cloudstorage.fbb;

public class NoteForm {

  private String title;
  private String description;

  public NoteForm(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
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
    return "NoteForm{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
