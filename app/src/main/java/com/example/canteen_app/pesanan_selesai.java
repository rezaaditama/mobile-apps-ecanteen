package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class pesanan_selesai extends AppCompatActivity {
    private ImageButton btnKembaliSelesai;
    private Button btnKembaliKeBeranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesanan_selesai);

        btnKembaliSelesai  = findViewById(R.id.btnBackPesananSelesai);
        btnKembaliKeBeranda  = findViewById(R.id.btnKembaliKeBerandaSelesai);

        //Button back
        btnKembaliSelesai.setOnClickListener(v -> {
            Intent intent = new Intent(pesanan_selesai.this, proses_ambil.class);
            startActivity(intent);
        });

        //Button Terima Pesanan
        btnKembaliKeBeranda.setOnClickListener(v -> {
            Intent intent = new Intent(pesanan_selesai.this, BerandaPenjualActivity.class);
            startActivity(intent);
        });
    }
}