package com.example.myapplication.CallBackFragment;

import androidx.fragment.app.Fragment;

public interface CallBackFragment{
    void loadFragment(Fragment fr);
    void initFragments(Long id, String login, String numgroup, String course);
}
