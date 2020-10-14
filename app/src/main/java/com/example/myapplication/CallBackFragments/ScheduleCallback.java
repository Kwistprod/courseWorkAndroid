package com.example.myapplication.CallBackFragments;

import com.example.myapplication.models.schedule.DaysOfWeek.Day;

import java.util.List;

public interface ScheduleCallback {
    void initAdapter(List<Day> days);
}
