package com.example.myapplication.NetworkService.repository;

import com.example.myapplication.models.schedule.DaysOfWeek.Day;
import com.example.myapplication.models.schedule.Schedule;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ScheduleRepository {
    @GET("schedule/{id}")
    Call<List<Day>> getSchedule(@Path("id") Long id);
    @POST("schedule")
    Call<Map<String, String>> uploadSchedule(@Body Schedule schedule);

}
