package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class proses_pesanan extends AppCompatActivity {
    private ImageButton btnKembaliProses;
    private Button btnSiapDiambil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proses_pesanan);

        btnKembaliProses  = findViewById(R.id.btnBackProsesPesananPenjual);
        btnSiapDiambil   = findViewById(R.id.btnAmbilProsesPesanan);

        //Button back
        btnKembaliProses.setOnClickListener(v -> {
            Intent intent = new Intent(proses_pesanan.this, pesanan.class);
            startActivity(intent);
        });

        //Button Siap Diambil
        btnSiapDiambil.setOnClickListener(v -> {
            Intent intent = new Intent(proses_pesanan.this, proses_ambil.class);
            startActivity(intent);
        });
    }
}