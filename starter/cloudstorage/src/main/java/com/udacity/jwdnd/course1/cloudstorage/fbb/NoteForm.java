package com.udacity.jwdnd.course1.cloudstorage.fbb;

public class NoteForm {

  private Long id;

  private String title;
  private String description;

  public NoteForm(Long id, String title, String description) {
    this.id = id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "NoteForm{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
