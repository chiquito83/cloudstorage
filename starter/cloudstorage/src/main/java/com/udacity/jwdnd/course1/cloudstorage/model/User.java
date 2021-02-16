package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {

  private Long userid;
  private String username;
  private String firstName;
  private String lastName;
  private String salt;
  private String password;

  public User(Long userid, String username, String firstName, String lastName, String salt, String password) {
    this.userid = userid;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salt = salt;
    this.password = password;
  }

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static User hashAndSalt(User user, String hashedPassword, String salt) {
    return new User(user.getUserid(), user.getUsername(), user.getFirstName(), user.getLastName(), salt, hashedPassword);
  }

  public Note createNote(String title, String description) {
    return new Note(null, this.userid, title, description);
  }
}
