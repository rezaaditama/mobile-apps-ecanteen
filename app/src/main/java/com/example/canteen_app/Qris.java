package com.example.canteen_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Qris extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qris);

//        Kunci tombol back
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });

//        Persiapan Delay
        new Handler().postDelayed(() -> {
//            Ambil User ID dari SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("USER_ID", 1);

//            Ambil Data Dari Halaman Sebelumnya
            String metode = getIntent().getStringExtra("METODE_BAYAR");
            String jam = getIntent().getStringExtra("PICKUP_TIME");
            int total = CartManager.getInstance().getGlobalTotal();
            String idOrder = "ORD-" + System.currentTimeMillis();

//          Ambil menu dari keranjang
            List<Menu> keranjangSekarang = CartManager.getInstance().getCartList();

//            Buat objek untuk di push ke database
            Order orderBaru = new Order(idOrder, new ArrayList<>(keranjangSekarang), jam, total, metode, userId);

//            Kirim ke server
            RetrofitClient.getApiService().simpanPesanan(orderBaru).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
//                Kalau berhasil
                    if (response.isSuccessful()) {
                        CartManager.getInstance().clearCart();

                        Intent intent = new Intent(Qris.this, pembayaranselesai.class);
                        intent.putExtra("ID", idOrder);
                        intent.putExtra("TOTAL", total);
                        intent.putExtra("METODE", metode);
                        intent.putExtra("JAM", jam);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Qris.this, "Gagal sinkron server", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(Qris.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }, 3000);
    }
}