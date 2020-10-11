package com.example.myapplication.CallBackFragment;

import com.example.myapplication.models.note.Note;

import java.util.List;

public interface NotesCallback{
    void initNotesFragment(List<Note> notes);
}
