package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

  @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
  Note getNote(Long noteid);

  @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
  List<Note> getNotes(Long userid);

  @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{title}, #{description}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "noteid")
  int insert(Note note);

  @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
  int delete(Long noteid);

  @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteid}")
  int update(String title, String description, Long noteid);
}
