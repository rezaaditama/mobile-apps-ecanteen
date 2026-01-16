package com.example.canteen_app;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body HashMap<String, String> body);

    @POST("auth/register")
    Call<LoginResponse> registerUser(@Body HashMap<String, String> body);
}