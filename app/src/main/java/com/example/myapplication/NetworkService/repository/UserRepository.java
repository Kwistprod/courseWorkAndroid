package com.example.myapplication.NetworkService.repository;

import com.example.myapplication.models.user.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserRepository {
    @POST("account/reg")
    Call<Map<String, Object>> RegisterUser(@Body User user);

    @POST("account/user")
    Call<User> AuthUser(@Body User user);

    @PUT("account/user/{id}")
    Call<User> UpdateUser(@Path("id") Long id, @Body User user);
}
