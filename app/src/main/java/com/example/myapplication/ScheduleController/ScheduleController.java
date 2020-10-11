package com.example.myapplication.ScheduleController;

import android.widget.Toast;

import com.example.myapplication.CallBackFragment.ScheduleCallback;
import com.example.myapplication.NetworkService.NetworkController;
import com.example.myapplication.models.schedule.DaysOfWeek.Day;
import com.example.myapplication.models.schedule.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleController {

    public static void fetchData(Long id, ScheduleCallback sc){
        NetworkController.getInstance().getScheduleRepository().getSchedule(id).enqueue(new Callback<List<Day>>() {
            @Override
            public synchronized void onResponse(Call<List<Day>> call, Response<List<Day>> response) {
                List<Day> days = new ArrayList<>();
                if(response.code() == 200){
                    days = response.body();
                }
                else {
                    days.add(new Day("Monday"));
                    days.add(new Day("Tuesday"));
                    days.add(new Day("Wednesday"));
                    days.add(new Day("Thursday"));
                    days.add(new Day("Friday"));
                    days.add(new Day("Saturday"));
                }
                sc.initAdapter(days);
            }

            @Override
            public void onFailure(Call<List<Day>> call, Throwable t) {
                System.out.println(t.toString());
                List<Day> days = new ArrayList<>();
                days.add(new Day("Monday"));
                days.add(new Day("Tuesday"));
                days.add(new Day("Wednesday"));
                days.add(new Day("Thursday"));
                days.add(new Day("Friday"));
                days.add(new Day("Saturday"));
                sc.initAdapter(days);
            }
        });
    }

    public static void uploadSchedule(List<Day> days, Long id){
        Schedule schedule = createSchedule(days, id);
        NetworkController.getInstance().getScheduleRepository().uploadSchedule(schedule).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                System.out.println("done");
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {

            }
        });
    }

    private static Schedule createSchedule(List<Day> days, Long id){
        String mon = parseDay(days.get(0));
        String tue = parseDay(days.get(1));
        String wed = parseDay(days.get(2));
        String thu = parseDay(days.get(3));
        String fri = parseDay(days.get(4));
        String sat = parseDay(days.get(5));
        return new Schedule(id, mon, tue, wed, thu, fri, sat);
    }
    private static String parseDay(Day day){
        StringBuilder parsedDay = new StringBuilder();
        if(!day.getClass1().equals("")){
            parsedDay.append(1).append(day.getClass1());
        }
        if(!day.getClass2().equals("")){
            parsedDay.append(2).append(day.getClass2());
        }
        if(!day.getClass3().equals("")){
            parsedDay.append(3).append(day.getClass3());
        }
        if(!day.getClass4().equals("")){
            parsedDay.append(4).append(day.getClass4());
        }
        if(!day.getClass5().equals("")){
            parsedDay.append(5).append(day.getClass5());
        }
        if(!day.getClass6().equals("")){
            parsedDay.append(6).append(day.getClass6());
        }
        return String.valueOf(parsedDay);
    }

}
