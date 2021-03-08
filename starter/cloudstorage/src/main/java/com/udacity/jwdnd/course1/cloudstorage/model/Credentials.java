package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credentials {
  private Long credentialid;

  private String url;
  private String username;
  private String password;

  private String key;

  private Long userid;

  private String encodedPassword;

  public Credentials(Long id, String url, String username, String password, String key, Long userid) {
    this.credentialid = id;
    this.url = url;
    this.username = username;
    this.password = password;
    this.key = key;
    this.userid = userid;
  }

  public Long getCredentialid() {
    return credentialid;
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

  public void setCredentialid(Long credentialid) {
    this.credentialid = credentialid;
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

  public void setEncodedPassword() {
    encodedPassword = this.password;
  }

  public String getEncodedPassword() {
    return encodedPassword;
  }

  @Override
  public String toString() {
    return "Credentials{" +
            "id=" + credentialid +
            ", url='" + url + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", key='" + key + '\'' +
            ", userid=" + userid +
            '}';
  }
}
