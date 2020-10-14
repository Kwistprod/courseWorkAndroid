package com.example.myapplication.CallBackFragments;

import androidx.fragment.app.Fragment;

public interface CallBackFragment{
    void loadFragment(Fragment fr);
    void initFragments(Long id, String login, String numgroup, String course);
}
