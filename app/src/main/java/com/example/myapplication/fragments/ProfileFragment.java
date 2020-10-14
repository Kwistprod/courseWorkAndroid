package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.CallBackFragments.CallBackFragment;
import com.example.myapplication.CallBackFragments.ChangeState.ChangeState;
import com.example.myapplication.NetworkService.NetworkController;
import com.example.myapplication.R;
import com.example.myapplication.Controllers.UserController.UserController;
import com.example.myapplication.models.user.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    private static final String ARG_ID = "id";
    private static final String ARG_LOGIN = "login";
    private static final String ARG_COURSE = "course";
    private static final String ARG_NUMGROUP = "numgroup";

    private Long mID;
    private String mLogin;
    private String mCourse;
    private String mNumgroup;
    private TextInputEditText login;
    private TextInputEditText password;
    private TextInputEditText course;
    private TextInputEditText numgroup;
    private MaterialButton bAccept;
    private MaterialButton bLogout;
    private ChangeState cs;
    private CallBackFragment cf;

    public ProfileFragment(ChangeState cs, CallBackFragment cf) {
        this.cs = cs;
        this.cf = cf;
    }
    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(Long param1, String param2, String param3, String param4, ChangeState cs, CallBackFragment cf) {
        ProfileFragment fragment = new ProfileFragment(cs, cf);
        Bundle args = new Bundle();
        args.putLong(ARG_ID, param1);
        args.putString(ARG_LOGIN, param2);
        args.putString(ARG_COURSE, param3);
        args.putString(ARG_NUMGROUP, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mID = getArguments().getLong(ARG_ID);
            mLogin = getArguments().getString(ARG_LOGIN);
            mCourse = getArguments().getString(ARG_COURSE);
            mNumgroup = getArguments().getString(ARG_NUMGROUP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        login = (TextInputEditText) view.findViewById(R.id.inLogin);
        password = (TextInputEditText) view.findViewById(R.id.inPassword);
        course = (TextInputEditText) view.findViewById(R.id.inCourse);
        numgroup = (TextInputEditText) view.findViewById(R.id.inGroup);
        login.setText(mLogin);
        course.setText(mCourse);
        numgroup.setText(mNumgroup);
        bLogout = (MaterialButton) view.findViewById(R.id.profile_logout);
        bAccept = (MaterialButton) view.findViewById(R.id.profile_accept);
        bAccept.setOnClickListener(v->{
            User u = UserController.getInstance();
            if(!login.getText().equals("") && login.getText() != null) {
                u.setLogin(login.getText().toString());
                u.setPassword(password.getText().toString().isEmpty() ? "" : password.getText().toString());
                u.setNumgroup(numgroup.getText().toString().isEmpty() ? "" : numgroup.getText().toString());
                u.setCourse(course.getText().toString().isEmpty() ? "" : course.getText().toString());

                NetworkController.getInstance().getUserRepository().UpdateUser(u.getId(), u).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        UserController.updateUser(user);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        bLogout.setOnClickListener(v->{
            cf.loadFragment(new RegisterFragment(cf, cs));
            cs.ChangeLoginState();
            UserController.resetUser();
        });

        return view;
    }
}