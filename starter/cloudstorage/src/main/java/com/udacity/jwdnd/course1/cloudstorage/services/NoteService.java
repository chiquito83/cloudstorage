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

    List<Note> n = noteMapper.getNotes(userid);

    if (n.size() > 0) {
      System.out.println("note 0 :   " + n.get(0));
    }

    return noteMapper.getNotes(userid);
  }


}
