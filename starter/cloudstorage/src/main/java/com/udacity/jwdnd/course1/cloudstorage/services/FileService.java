package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

  private FileMapper fileMapper;

  public FileService(FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  public int addFile(File file) {
    int r = fileMapper.saveFile(file);

    return r;

  }

  public File readFile(Long id) {
    return fileMapper.getFile(id);
  }

  public List<File> getFiles(Long userid) {
    return fileMapper.getAllFiles(userid);
  }

}
