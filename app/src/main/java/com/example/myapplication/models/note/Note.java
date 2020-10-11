package com.example.myapplication.models.note;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {
    @SerializedName("user_id")
    @Expose
    private long user_id;

    @SerializedName("note_id")
    @Expose
    private long note_id;

    @SerializedName("note")
    @Expose
    private String note;

    public Long getNote_id() {
        return note_id;
    }

    public void setNote_id(Long note_id) {
        this.note_id = note_id;
    }
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public Note(Long id,String note){
        this.user_id = id;
        this.note = note;
    }
}
