package com.example.canteen_app;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    // Ambil daftar pesanan berdasarkan User ID
    @GET("api/orders/user/{user_id}")
    Call<List<Order>> getOrdersByUser(@Path("user_id") int userId);

//    API Login dan register
    @POST("api/register")
    Call<Void> register(@Body User user);

    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

//    update data user
    @FormUrlEncoded
    @POST("api/update-user")
    Call<UpdateResponse> updateUser(
            @Field("id") int id,
            @Field("nama_lengkap") String nama,
            @Field("email") String email,
            @Field("nomor_telepon") String telepon
    );

//    Payment Gateway
    @POST("api/orders")
    Call<OrderResponse> simpanPesanan(@Body Order order);

//    Dashboard admin
    @GET("api/penjual/dashboard/{user_id}")
    Call<BerandaPenjualResponse> getDashboardPenjual(@Path("user_id") int userId);

//    List pesanan aktif penjual
    @GET("api/penjual/pesanan-aktif/{user_id}")
    Call<List<BerandaPenjualResponse.OrderModel>> getPesananAktif(@Path("user_id") int userId);

//    Ambil detail pesanan
    @GET("api/penjual/order-detail/{order_id}")
    Call<BerandaPenjualResponse.OrderModel> getOrderDetail(@Path("order_id") String orderId);

//    Update status pesanan jadi selesai
    @FormUrlEncoded
    @POST("api/penjual/selesaikan-pesanan")
    Call<Void> updateStatusSelesai(@Field("order_id") String orderId);

//    Untuk riwayat pemesanan
    @GET("api/penjual/riwayat/{user_id}")
    Call<List<BerandaPenjualResponse.OrderModel>> getRiwayatPenjualan(@Path("user_id") int userId);

//    Update profil penjual
    @FormUrlEncoded
    @POST("api/penjual/update-profile")
    Call<UpdateResponse> updateProfilePenjual(
            @Field("id") int id,
            @Field("nama_lengkap") String nama,
            @Field("email") String email,
            @Field("nomor_telepon") String telepon,
            @Field("shop_name") String shopName
    );
}
