package com.example.myapplication.UserController;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.myapplication.models.user.User;

public class UserPreference {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private final String SP_NAME = "settings";

    public UserPreference(Context context){
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void getUserData(@NonNull User u){
        u.setId(sp.getLong("id", -1));
        u.setLogin(sp.getString("login", "not logged in"));
        u.setCourse(sp.getString("course", ""));
        u.setNumgroup(sp.getString("numgroup", ""));
    }

    public void setUserData(@NonNull User u){

        editor.putLong("id",u.getId());
        editor.putString("login", u.getLogin());
        editor.putString("numgroup", u.getNumgroup().isEmpty() ? "" : u.getNumgroup());
        editor.putString("course", u.getCourse().isEmpty() ? "" : u.getCourse());
        editor.apply();

    }

    public void clear(){
        editor.clear();
        editor.apply();
    }

}
