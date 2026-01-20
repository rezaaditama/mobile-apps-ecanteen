package com.example.canteen_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PesananAktifActivity extends AppCompatActivity {
    private LinearLayout tabPesananSelesai;

    private Button btnQris, btnCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesanan_aktif);

        tabPesananSelesai  = findViewById(R.id.tabSelesai);
        btnQris            = findViewById(R.id.btn_pesanan_qris);
        btnCod             = findViewById(R.id.btn_pesanan_cod);

        tabPesananSelesai.setOnClickListener(v -> {
            Intent intent = new Intent(PesananAktifActivity.this, PesananSelesaiActivity.class);
            startActivity(intent);
        });

        btnQris.setOnClickListener(v -> {
            Intent intent = new Intent(PesananAktifActivity.this, StatusQrisActivity.class);
            startActivity(intent);
        });

        btnCod.setOnClickListener(v -> {
            Intent intent = new Intent(PesananAktifActivity.this, StatusCodActivity.class);
            startActivity(intent);
        });
    }
}