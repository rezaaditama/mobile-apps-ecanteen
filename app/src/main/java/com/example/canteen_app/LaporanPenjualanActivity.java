package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LaporanPenjualanActivity extends AppCompatActivity {

    private Button btnDetail_1, btnDetail_2;
    private ImageView backBeranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_laporan_penjualan);

        btnDetail_1     = findViewById(R.id.btnDetailCod);
        btnDetail_2     = findViewById(R.id.btnDetailQris);
        backBeranda     = findViewById(R.id.btn_back_beranda);

        btnDetail_1.setOnClickListener(v -> {
            Intent intent = new Intent(LaporanPenjualanActivity.this, DetailPemesananActivity.class);
            startActivity(intent);
        });

        btnDetail_2.setOnClickListener(v -> {
            Intent intent = new Intent(LaporanPenjualanActivity.this, DetailQrisActivity.class);
            startActivity(intent);
        });

        backBeranda.setOnClickListener(v -> {
            Intent intent = new Intent(LaporanPenjualanActivity.this, BerandaPenjualActivity.class);
            startActivity(intent);
        });
    }
}