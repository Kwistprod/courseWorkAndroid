package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.ChangeState.ChangeState;
import com.example.myapplication.UserController.UserController;
import com.example.myapplication.UserController.UserPreference;
import com.example.myapplication.fragments.NotesFragment;
import com.example.myapplication.fragments.ProfileFragment;
import com.example.myapplication.fragments.RegisterFragment;
import com.example.myapplication.fragments.ScheduleFragment;
import com.example.myapplication.CallBackFragment.CallBackFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements CallBackFragment, ChangeState {

    private BottomNavigationView  bottomNavigationView;
    private ProfileFragment profileFragment;
    private NotesFragment notesFragment;
    private ScheduleFragment scheduleFragment;
    private RegisterFragment registerFragment;
    private boolean isLogin = false;
    private ActionBar actionBar;
    private UserPreference userPreference;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPreference = new UserPreference(getApplicationContext());
        userController = new UserController(userPreference);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bNavListener);

        actionBar = getSupportActionBar();
        long user_id = UserAuth();
        if(user_id == -1){
            registerFragment = new RegisterFragment(this, this);
            actionBar.setTitle("Registration");
            loadFragment(registerFragment);
            bottomNavigationView.setSelectedItemId(R.id.Profile);
        } else {
            initFragments(UserController.getUserId(), UserController.getUserLogin(), UserController.getUserNumgroup(), UserController.getUserCourse());
            ChangeLoginState();
            actionBar.setTitle("Schedule");
            loadFragment(scheduleFragment);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment frag = null;
            switch(item.getItemId()){
                case R.id.Profile:
                    if(isLogin) {
                        actionBar.setTitle("Profile");
                        frag = profileFragment;
                    } else{
                        frag = registerFragment;
                    }
                    break;
                case R.id.Notes:
                    if(isLogin) {
                        actionBar.setTitle("Notes");
                        frag = notesFragment;
                    } else{
                        frag = registerFragment;
                    }
                    break;
                case R.id.Schedule:
                    if(isLogin) {
                        actionBar.setTitle("Schedule");
                        frag = scheduleFragment;
                    } else{
                        frag = registerFragment;
                    }
                    break;
            }
            loadFragment(frag);
            return true;
        }
    };

    @Override
    public void initFragments(Long id, String login, String numgroup, String course){
        registerFragment = new RegisterFragment(this, this);
        profileFragment = ProfileFragment.newInstance(id, login, course == null ? "" : course, numgroup == null ? "":numgroup, this, this);
        scheduleFragment = ScheduleFragment.newInstance(id);
        notesFragment = NotesFragment.newInstance(id);
        bottomNavigationView.setSelectedItemId(R.id.Schedule);
    }

    @Override
    public void ChangeLoginState(){
        this.isLogin = !this.isLogin;
    }

    protected Long UserAuth(){
        userController.initUser();
        return UserController.getUserId();
    }
    @Override
    public void loadFragment(Fragment fr){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, fr)
                .addToBackStack(null)
                .commit();
    }

}