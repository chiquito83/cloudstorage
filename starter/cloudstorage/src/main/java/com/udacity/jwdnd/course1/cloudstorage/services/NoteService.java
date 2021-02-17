package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

  private NoteMapper noteMapper;

  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  public int createNote(Note note) {
    int i =  noteMapper.insert(note);

    System.out.println("added note:" + getNote(note.getNoteid()));

    return i;

  }

  public Note getNote(Long id) {
    return noteMapper.getNote(id);
  }

  public List<Note> getNotes(Long userid) {


    return noteMapper.getNotes(userid);
  }

  public int deleteNote(Long id) {

    return noteMapper.delete(id);

  }


}
