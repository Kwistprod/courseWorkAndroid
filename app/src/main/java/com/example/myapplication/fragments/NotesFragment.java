package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.CallBackFragment.NotesCallback;
import com.example.myapplication.Controllers.NotesController.NotesController;
import com.example.myapplication.Controllers.NotesController.RVNOTEAdapter;
import com.example.myapplication.R;
import com.example.myapplication.models.note.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment implements NotesCallback {

    private static final String ARG_ID = "id";

    private Long mID;
    private RecyclerView rv;
    private boolean isLoaded = false;
    private List<Note> notes;
    private View view;
    private RVNOTEAdapter rva;

    public NotesFragment()
    {

    }

    public static NotesFragment newInstance(Long param1) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mID = getArguments().getLong(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notes, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv_notes);
        if(isLoaded == false) {
            NotesController.fetchData(this.mID, this);
        }else {
            initNotesFragment(notes);
        }
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v->{
            Note n = new Note(this.mID, "");
            rva.addNote(n);
        });
        Button but = (Button) view.findViewById(R.id.scheduleSave);
        but.setOnClickListener(v->{
            NotesController.uploadNote(rva.getNotes());
        });
        return view;
    }

    @Override
    public void initNotesFragment(List<Note> notes) {
        this.notes = notes;
        isLoaded = true;
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);
        rva = new RVNOTEAdapter(notes);
        rv.setAdapter(rva);
    }
}