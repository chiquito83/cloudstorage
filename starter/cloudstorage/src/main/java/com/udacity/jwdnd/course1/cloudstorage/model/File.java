package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Arrays;

public class File {

  private Long fileid;
  private String fileName;
  private String contentType;
  private String fileSize;
  private Long userid;
  private byte[] fileData;

  public File(Long fileid, String fileName, String contentType, String fileSize, Long userid, byte[] fileData) {
    this.fileid = fileid;
    this.fileName = fileName;
    this.contentType = contentType;
    this.fileSize = fileSize;
    this.userid = userid;
    this.fileData = fileData;
  }

  public Long getFileid() {
    return fileid;
  }

  public void setFileid(Long fileid) {
    this.fileid = fileid;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getFileSize() {
    return fileSize;
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public byte[] getFileData() {
    return fileData;
  }

  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

  @Override
  public String toString() {
    return "File{" +
            "fileid=" + fileid +
            ", fileName='" + fileName + '\'' +
            ", contentType='" + contentType + '\'' +
            ", fileSize='" + fileSize + '\'' +
            ", userid=" + userid +
            '}';
  }
}
