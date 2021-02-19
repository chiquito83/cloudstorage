package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

  @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
  File getFile(Long fileid); //todo check

  @Select("SELECT * FROM FILES WHERE userid = #{userid}")
  List<File> getAllFiles(Long userid);

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
          " VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
  @Options(useGeneratedKeys = true, keyProperty = "fileid")
  int saveFile(File file);

  @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
  int deleteFile(Long fileid);
}
