package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.CallBackFragment.CallBackFragment;
import com.example.myapplication.CallBackFragment.ChangeState.ChangeState;
import com.example.myapplication.NetworkService.NetworkController;
import com.example.myapplication.NetworkService.repository.UserRepository;
import com.example.myapplication.R;
import com.example.myapplication.Controllers.UserController.UserController;
import com.example.myapplication.models.user.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    private View root;
    private TextInputEditText login;
    private TextInputEditText password;
    private Button bLogin;
    private Button bSignup;
    private CallBackFragment cf;
    private ChangeState cs;

    public RegisterFragment(CallBackFragment cf, ChangeState cs) {
        this.cf = cf;
        this.cs = cs;
    }

//    public static RegisterFragment newInstance() {
//        RegisterFragment fragment = new RegisterFragment();
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        root = inflater.inflate(R.layout.fragment_register, container, false);
        login = root.findViewById(R.id.login_register);
        password = root.findViewById(R.id.password_register);
        bLogin = root.findViewById(R.id.login_button);
        bSignup = root.findViewById(R.id.signup_button);
        User user = UserController.getInstance();
        UserRepository ur = NetworkController.getInstance().getUserRepository();
        bLogin.setOnClickListener(v->{
            try {
                user.setLogin(Objects.requireNonNull(login.getText()).toString());
                user.setPassword(Objects.requireNonNull(password.getText()).toString());
            }catch (Exception e){
                Toast.makeText(getActivity().getApplicationContext(), "All fields should be filled", Toast.LENGTH_SHORT).show();
            }
            ur.AuthUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User tmp = response.body();
                    try{
                        if(tmp.getId() != null && tmp.getId() != 0){
                            user.setId(tmp.getId());
                            user.setLogin(tmp.getLogin());
                            user.setCourse(tmp.getCourse() == null ? "" : tmp.getCourse());
                            user.setNumgroup(tmp.getNumgroup() == null ? "" : tmp.getNumgroup());
                            UserController.updateUser(user);
                            cs.ChangeLoginState();
                            cf.initFragments(user.getId(), user.getLogin(), user.getNumgroup(), user.getCourse());
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), "Error occurred during user auth", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            UserController.resetUser();
                        }

                    } catch(Exception e){
                        Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        UserController.resetUser();
                        password.setText("");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    UserController.resetUser();
                    password.setText("");
                }
            });

        }

        );
        bSignup.setOnClickListener((v -> {
            try {
                user.setLogin(Objects.requireNonNull(login.getText()).toString());
                user.setPassword(Objects.requireNonNull(password.getText()).toString());
            }catch (Exception e){
                Toast.makeText(getActivity().getApplicationContext(), "All fields should be filled", Toast.LENGTH_SHORT).show();
            }
            ur.RegisterUser(user).enqueue(new Callback<Map<String, Object>>() {
                @Override
                public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                    Map<String, Object> map = response.body();
                    try{
                        if(Objects.equals(map.get("result"), "done")) {
                            user.setId(((Double) map.get("id")).longValue());
                            UserController.updateUser(user);
                            cs.ChangeLoginState();
                            cf.initFragments(user.getId(), user.getLogin(), user.getNumgroup(), user.getCourse());
                            cf.loadFragment(new ScheduleFragment());
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(), "Error occurred during user auth", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            UserController.resetUser();
                        }
                    }catch(Exception e){
                        Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        UserController.resetUser();
                        password.setText("");
                    }
                }

                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                    Toast.makeText(getActivity().getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    UserController.resetUser();
                    password.setText("");
                }
            });
        }));
        return root;
    }

}