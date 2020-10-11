package com.example.myapplication.models.schedule.DaysOfWeek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("class1")
    @Expose
    private String class1;
    @SerializedName("class2")
    @Expose
    private String class2;
    @SerializedName("class3")
    @Expose
    private String class3;
    @SerializedName("class4")
    @Expose
    private String class4;
    @SerializedName("class5")
    @Expose
    private String class5;
    @SerializedName("class6")
    @Expose
    private String class6;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getClass2() {
        return class2;
    }

    public void setClass2(String class2) {
        this.class2 = class2;
    }

    public String getClass3() {
        return class3;
    }

    public void setClass3(String class3) {
        this.class3 = class3;
    }

    public String getClass4() {
        return class4;
    }

    public void setClass4(String class4) {
        this.class4 = class4;
    }

    public String getClass5() {
        return class5;
    }

    public void setClass5(String class5) {
        this.class5 = class5;
    }

    public String getClass6() {
        return class6;
    }

    public void setClass6(String class6) {
        this.class6 = class6;
    }

    public Day(){
        this.day = "";
        this.class1 = "";
        this.class2 = "";
        this.class3 = "";
        this.class4 = "";
        this.class5 = "";
        this.class6 = "";
    }
    public Day(String dayOfWeek){
        this.day = dayOfWeek;
        this.class1 = "";
        this.class2 = "";
        this.class3 = "";
        this.class4 = "";
        this.class5 = "";
        this.class6 = "";
    }

}
