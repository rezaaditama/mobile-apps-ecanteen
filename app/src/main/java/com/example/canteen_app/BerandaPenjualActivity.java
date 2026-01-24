package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class BerandaPenjualActivity extends AppCompatActivity {
    private LinearLayout btnLaporanPenjualan, btnProsesPesanan, btnPesananAktif1;
    private TextView tvLihatSemua;
    private LinearLayout navMenu, navPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beranda_penjual);

        btnLaporanPenjualan = findViewById(R.id.btn_beranda_penjual_laporan_penjualan);
        btnProsesPesanan    = findViewById(R.id.btn_beranda_penjualan_proses_pesanan);
        btnPesananAktif1    = findViewById(R.id.btn_beranda_penjual_pesanan_aktif1);
        tvLihatSemua        = findViewById(R.id.tv8_beranda_penjualan);
        navMenu             = findViewById(R.id.menu_penjual);
        navPenjual          = findViewById(R.id.menu_profile);

        //Button Laporan Penjualan
        btnLaporanPenjualan.setOnClickListener(v -> {
            Intent intent = new Intent(BerandaPenjualActivity.this, LaporanPenjualanActivity.class);
            startActivity(intent);
        });

        //Button Proses Pesanan
        btnProsesPesanan.setOnClickListener(v -> {
            Intent intent = new Intent(BerandaPenjualActivity.this, pesanan.class);
            startActivity(intent);
        });

        //Button Pesanan Aktif 1
        btnPesananAktif1.setOnClickListener(v -> {
            Intent intent = new Intent(BerandaPenjualActivity.this, proses_pesanan.class);
            startActivity(intent);
        });


        //TextView Lihat Semua
        tvLihatSemua.setOnClickListener(v -> {
            Intent intent = new Intent(BerandaPenjualActivity.this, pesanan.class);
            startActivity(intent);
        });


        //navButton Menu Penjualan
        navMenu.setOnClickListener(v -> {
            Intent intent = new Intent(BerandaPenjualActivity.this, MenuPenjual.class);
            startActivity(intent);
        });

        //navButton Profil Penjual
        navPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(BerandaPenjualActivity.this, ProfilPenjualActivity.class);
            startActivity(intent);
        });
    }
}