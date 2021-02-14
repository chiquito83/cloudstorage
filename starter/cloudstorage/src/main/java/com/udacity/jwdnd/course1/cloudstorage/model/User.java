package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {

  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String salt;
  private String password;

  public User(Long id, String username, String firstName, String lastName, String salt, String password) {
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salt = salt;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return new User(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), salt, hashedPassword);
  }
}
