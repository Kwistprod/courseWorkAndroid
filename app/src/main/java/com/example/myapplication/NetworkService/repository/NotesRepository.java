package com.example.myapplication.NetworkService.repository;

import com.example.myapplication.models.note.Note;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotesRepository {
    @GET("notes/{id}")
    Call<List<Note>> getAll(@Path("id") Long id);
    @POST("notes")
    Call<Map<String, String>> addNote(@Body List<Note> notes);
}
