package com.example.canteen_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanPenjualanActivity extends AppCompatActivity {

//    Inisialisasi Variabel
    private RecyclerView rvLaporan;
    private LaporanAdapter adapter;
    private ImageView btnBack;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_laporan_penjualan);

//      Ambil komponen berdasarkan ID
        rvLaporan = findViewById(R.id.rv_laporan);
        btnBack = findViewById(R.id.btn_back_beranda);

//        Setup RecyclerView
        rvLaporan.setLayoutManager(new LinearLayoutManager(this));

//        Ambil user ID
        SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("USER_ID", -1);

//        Untuk back
        btnBack.setOnClickListener(v -> finish());

//        Fetching data
        if (userId != -1) {
            fetchRiwayatPenjualan();
        } else {
            Toast.makeText(this, "Sesi tidak valid", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchRiwayatPenjualan() {
        RetrofitClient.getApiService().getRiwayatPenjualan(userId).enqueue(new Callback<List<BerandaPenjualResponse.OrderModel>>() {
            @Override
            public void onResponse(Call<List<BerandaPenjualResponse.OrderModel>> call, Response<List<BerandaPenjualResponse.OrderModel>> response) {
//                Kalau benar
                if (response.isSuccessful() && response.body() != null) {
                    List<BerandaPenjualResponse.OrderModel> list = response.body();
                    adapter = new LaporanAdapter(list);
                    rvLaporan.setAdapter(adapter);
                } else {
                    Toast.makeText(LaporanPenjualanActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

//            Kalau gagal
            @Override
            public void onFailure(Call<List<BerandaPenjualResponse.OrderModel>> call, Throwable t) {
                Toast.makeText(LaporanPenjualanActivity.this, "Gagal memuat laporan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
