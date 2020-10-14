package com.example.myapplication.CallBackFragments;

import com.example.myapplication.models.note.Note;

import java.util.List;

public interface NotesCallback{
    void initNotesFragment(List<Note> notes);
}
