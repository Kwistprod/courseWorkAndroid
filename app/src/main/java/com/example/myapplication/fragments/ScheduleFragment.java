package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.CallBackFragment.ScheduleCallback;
import com.example.myapplication.R;
import com.example.myapplication.ScheduleController.RVAdapter;
import com.example.myapplication.ScheduleController.ScheduleController;
import com.example.myapplication.models.schedule.DaysOfWeek.Day;

import java.util.List;


public class ScheduleFragment extends Fragment implements ScheduleCallback {


    private static final String ARG_ID = "id";

    private Long mID;
    private View view;
    private RecyclerView rv;
    private boolean isLoaded = false;
    private List<Day> days;
    private RVAdapter rva;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    public static ScheduleFragment newInstance(Long param1) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mID = getArguments().getLong(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        if(isLoaded == false) {
            ScheduleController.fetchData(this.mID, this);
        }else {
            initAdapter(days);
        }
        Button bSave = (Button) view.findViewById(R.id.scheduleSave);
        bSave.setOnClickListener(v->{
            ScheduleController.uploadSchedule(rva.getData(), this.mID);
        });

        return view;
    }

    public void initAdapter(List<Day> days){
        this.days = days;
        isLoaded = true;
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        rv.setLayoutManager(llm);
        rva = new RVAdapter(days);
        rv.setAdapter(rva);
    }



    }