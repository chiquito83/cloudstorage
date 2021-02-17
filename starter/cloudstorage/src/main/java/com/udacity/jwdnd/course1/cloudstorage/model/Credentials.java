package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
  private Long credentialsid;

  private String url;
  private String username;
  private String password;

  private String key;

  private Long userid;

  public Credentials(Long id, String url, String username, String password, String key, Long userid) {
    this.credentialsid = id;
    this.url = url;
    this.username = username;
    this.password = password;
    this.key = key;
    this.userid = userid;
  }

  public Long getCredentialsid() {
    return credentialsid;
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

  public void setKey(String key) {
    this.key = key;
  }

  public void setCredentialsid(Long credentialsid) {
    this.credentialsid = credentialsid;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  @Override
  public String toString() {
    return "Credentials{" +
            "id=" + credentialsid +
            ", url='" + url + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", key='" + key + '\'' +
            ", userid=" + userid +
            '}';
  }
}
