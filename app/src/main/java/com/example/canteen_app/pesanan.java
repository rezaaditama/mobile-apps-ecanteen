package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pesanan extends AppCompatActivity {
    private ImageButton btnKembaliPesanan;
    private RecyclerView rvPesanan;
    private PesananAktifPenjualAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesanan);

//        Ambil komponen berdasarkan id
        btnKembaliPesanan = findViewById(R.id.btnBackPesanan);
        rvPesanan = findViewById(R.id.rv_pesanan_aktif);

//        Untuk recycle view
        rvPesanan.setLayoutManager(new LinearLayoutManager(this));

//        Tombol kembali ke beranda
        btnKembaliPesanan.setOnClickListener(v -> finish());

//        Ambil data
        fetchDataPesanan();
    }

    private void fetchDataPesanan() {
//        Ambil user id
        SharedPreferences sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        int userId = sharedPref.getInt("USER_ID", -1);
        RetrofitClient.getApiService().getPesananAktif(userId).enqueue(new Callback<List<BerandaPenjualResponse.OrderModel>>() {
            @Override
            public void onResponse(Call<List<BerandaPenjualResponse.OrderModel>> call, Response<List<BerandaPenjualResponse.OrderModel>> response) {

//                Kalau berhasil
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new PesananAktifPenjualAdapter(response.body(), pesanan.this);
                    rvPesanan.setAdapter(adapter);
                } else {
                    Toast.makeText(pesanan.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

//            Kalau gagal
            @Override
            public void onFailure(Call<List<BerandaPenjualResponse.OrderModel>> call, Throwable t) {
                Toast.makeText(pesanan.this, "Koneksi Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}