package com.example.canteen_app;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    // Path daftar toko
    @GET("api/toko")
    Call<List<Toko>> getDaftarToko();

//    Path Daftar menu berdasarkan ID
    @GET("api/menu/{shop_id}")
    Call<List<Menu>> getMenuByToko(@Path("shop_id") int shopId);

//    Post Order ke Database
    @POST("api/orders")
    Call<Void> simpanPesanan(@Body Order order);

    // Ambil daftar pesanan berdasarkan User ID
    @GET("api/orders/user/{user_id}")
    Call<List<Order>> getOrdersByUser(@Path("user_id") int userId);
}
