package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
  private Long id;

  private String url;
  private String username;
  private String password;

  private String key;

  private Long userid;

  public Credentials(Long id, String url, String username, String password, String key, Long userid) {
    this.id = id;
    this.url = url;
    this.username = username;
    this.password = password;
    this.key = key;
    this.userid = userid;
  }

  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getUserid() {
    return userid;
  }

  public String getKey() {
    return key;
  }

  @Override
  public String toString() {
    return "Credentials{" +
            "id=" + id +
            ", url='" + url + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", key='" + key + '\'' +
            ", userid=" + userid +
            '}';
  }
}
