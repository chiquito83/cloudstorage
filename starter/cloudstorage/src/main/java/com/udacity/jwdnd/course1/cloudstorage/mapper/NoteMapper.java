package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
interface NoteMapper {

  @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
  Note getNote(Long noteid);

  @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
  List<Note> getNotes(Long userid);

  @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{title}, #{description}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "noteid")
  int insert(Note note);
}