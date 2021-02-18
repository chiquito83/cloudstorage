package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

  @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
  File getFile(Long id);

  @Select("SELECT * FROM FILES WHERE userid = #{userid}")
  List<File> getAllFiles(Long userid);

  @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
          " VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userid}, #{filedata})")
  @Options(useGeneratedKeys = true, keyProperty = "fileid")
  int saveFile(File file);
}
