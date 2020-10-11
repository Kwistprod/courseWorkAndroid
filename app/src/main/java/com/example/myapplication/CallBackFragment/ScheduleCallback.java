package com.example.myapplication.CallBackFragment;

import com.example.myapplication.models.schedule.DaysOfWeek.Day;

import java.util.List;

public interface ScheduleCallback {
    void initAdapter(List<Day> days);
}
