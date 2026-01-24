package com.example.canteen_app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
//    Inisialisasi API dan retrofit
    private static final String BASE_URL = "https://be-mobile-ecanteen.vercel.app/";
    private static Retrofit retrofit = null;

//    GET API
    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}

