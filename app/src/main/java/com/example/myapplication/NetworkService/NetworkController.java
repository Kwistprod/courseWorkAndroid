package com.example.myapplication.NetworkService;


import com.example.myapplication.NetworkService.repository.NotesRepository;
import com.example.myapplication.NetworkService.repository.ScheduleRepository;
import com.example.myapplication.NetworkService.repository.UserRepository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkController {

    private static NetworkController Instance;
    private Retrofit retrofit;
    private final String baseURL = "https://springapi.herokuapp.com/api/";

    NetworkController(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    };

    public static NetworkController getInstance(){
        if(Instance == null){
            Instance = new NetworkController();
        }
        return Instance;
    }

    public UserRepository getUserRepository(){
        return retrofit.create(UserRepository.class);
    }

    public ScheduleRepository getScheduleRepository(){
        return retrofit.create(ScheduleRepository.class);
    }
    public NotesRepository getNotesRepository(){
        return retrofit.create(NotesRepository.class);
    }

}
