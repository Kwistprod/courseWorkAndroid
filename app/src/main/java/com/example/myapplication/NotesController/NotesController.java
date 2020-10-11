package com.example.myapplication.NotesController;

import com.example.myapplication.CallBackFragment.NotesCallback;
import com.example.myapplication.NetworkService.NetworkController;
import com.example.myapplication.models.note.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesController {
    public static void fetchData(Long id, NotesCallback nc){
        NetworkController.getInstance().getNotesRepository().getAll(id).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                List<Note> notes = new ArrayList<>();
                try {
                    if (response.code() == 200 && response.body().size() > 0) {
                        notes = response.body();
                    } else {
                        notes.add(new Note(id, ""));
                    }
                }catch (NullPointerException e){
                    System.out.println(e.toString());
                }
                nc.initNotesFragment(notes);
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                List<Note> notes = new ArrayList<>();
                notes.add(new Note(id, ""));
                nc.initNotesFragment(notes);
            }
        });
    }
    public static void uploadNote(List<Note> notes){
        NetworkController.getInstance().getNotesRepository().addNote(notes).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if(response.code() == 200) {
                    System.out.println("done");
                }else{
                    System.out.println("something went wrong");
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });
    }
}
