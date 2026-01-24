package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class pesanan extends AppCompatActivity {
    private ImageButton btnKembaliPesanan;
    private Button btnTerimaPesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesanan);

        btnKembaliPesanan  = findViewById(R.id.btnBackPesanan);
        btnTerimaPesanan   = findViewById(R.id.btnTerimaPesananPesanan);

        //Button back
        btnKembaliPesanan.setOnClickListener(v -> {
            Intent intent = new Intent(pesanan.this, BerandaPenjualActivity.class);
            startActivity(intent);
        });

        //Button Terima Pesanan
        btnTerimaPesanan.setOnClickListener(v -> {
            Intent intent = new Intent(pesanan.this, proses_pesanan.class);
            startActivity(intent);
        });

    }
}