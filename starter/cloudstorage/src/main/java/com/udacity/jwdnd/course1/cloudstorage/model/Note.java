package com.udacity.jwdnd.course1.cloudstorage.model;


public class Note {

  private Long noteid;

  private Long userid;

  private String title;
  private String description;

  public Note(Long noteid, Long userid, String title, String description) {
    this.noteid = noteid;
    this.userid = userid;
    this.title = title;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Long getNoteid() {
    return noteid;
  }

  public Long getUserid() {
    return userid;
  }

  @Override
  public String toString() {
    return "Note{" +
            "id=" + noteid +
            ", userid=" + userid +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            '}';
  }


}
