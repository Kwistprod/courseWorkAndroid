package com.example.myapplication.Controllers.UserController;

import com.example.myapplication.models.user.User;

public class UserController {

    private static User user = new User();
    private static UserPreference pm;

    public UserController(UserPreference pm){
        this.pm = pm;
    }

    public static void initUser(){
        pm.getUserData(user);
    }

    public static void updateUser(User u){
        pm.setUserData(u);
    }
    public static User getInstance() {
        return user;
    }

    public static Long getUserId(){
        return user.getId();
    }
    public static String getUserLogin(){
        return user.getLogin();
    }
    public static String getUserCourse(){
        return user.getCourse();
    }
    public static String getUserNumgroup(){
        return user.getNumgroup();
    }
    public static void resetUser(){
        pm.clear();
        user.setId(-1L);
        user.setPassword("");
        user.setNumgroup("");
        user.setCourse("");
    }
}
