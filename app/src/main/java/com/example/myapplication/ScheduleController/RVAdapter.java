package com.example.myapplication.ScheduleController;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.schedule.DaysOfWeek.Day;
import com.example.myapplication.models.schedule.Schedule;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DayViewholder> {
    public static class DayViewholder extends RecyclerView.ViewHolder{
        CardView cv;
        MaterialTextView day;
        TextInputEditText class1;
        TextInputEditText class2;
        TextInputEditText class3;
        TextInputEditText class4;
        TextInputEditText class5;
        TextInputEditText class6;


        public DayViewholder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card);
            day = (MaterialTextView) itemView.findViewById(R.id.day);
            class1 = (TextInputEditText) itemView.findViewById(R.id.class1);
            class2 = (TextInputEditText) itemView.findViewById(R.id.class2);
            class3 = (TextInputEditText) itemView.findViewById(R.id.class3);
            class4 = (TextInputEditText) itemView.findViewById(R.id.class4);
            class5 = (TextInputEditText) itemView.findViewById(R.id.class5);
            class6 = (TextInputEditText) itemView.findViewById(R.id.class6);
        }

    }

    private List<Day> schedule;
    private DayViewholder dvh;
    public RVAdapter(List<Day> schedule){
        this.schedule = schedule;
    }

    @NonNull
    @Override
    public RVAdapter.DayViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards, parent, false);
        dvh = new DayViewholder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.DayViewholder holder, int i) {
        TextWatcher txtwatch1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                schedule.get(i).setClass1(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        TextWatcher txtwatch2 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                schedule.get(i).setClass2(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        TextWatcher txtwatch3 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                schedule.get(i).setClass3(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        TextWatcher txtwatch4 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                schedule.get(i).setClass4(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        TextWatcher txtwatch5 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                schedule.get(i).setClass5(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        TextWatcher txtwatch6 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                schedule.get(i).setClass6(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        holder.day.setText(schedule.get(i).getDay() == null ? "" : schedule.get(i).getDay());
        holder.class1.setText(schedule.get(i).getClass1() == null? "" :schedule.get(i).getClass1());
        holder.class2.setText(schedule.get(i).getClass2() == null? "" :schedule.get(i).getClass2());
        holder.class3.setText(schedule.get(i).getClass3() == null? "" :schedule.get(i).getClass3());
        holder.class4.setText(schedule.get(i).getClass4() == null? "" :schedule.get(i).getClass4());
        holder.class5.setText(schedule.get(i).getClass5() == null? "" :schedule.get(i).getClass5());
        holder.class6.setText(schedule.get(i).getClass6() == null? "" :schedule.get(i).getClass6());

        holder.class1.addTextChangedListener(txtwatch1);
        holder.class2.addTextChangedListener(txtwatch2);
        holder.class3.addTextChangedListener(txtwatch3);
        holder.class4.addTextChangedListener(txtwatch4);
        holder.class5.addTextChangedListener(txtwatch5);
        holder.class6.addTextChangedListener(txtwatch6);
    }

    public List<Day> getData(){
        return this.schedule;
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }




    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
