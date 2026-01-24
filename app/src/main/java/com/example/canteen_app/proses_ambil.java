package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class proses_ambil extends AppCompatActivity {
    private ImageButton btnKembaliAmbil;
    private Button btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proses_ambil);

        btnKembaliAmbil  = findViewById(R.id.btnBackProsesAmbil);
        btnSelesai       = findViewById(R.id.btnSiapDiambil);

        //Button back
        btnKembaliAmbil.setOnClickListener(v -> {
            Intent intent = new Intent(proses_ambil.this, proses_pesanan.class);
            startActivity(intent);
        });

        //Button Terima Pesanan
        btnSelesai.setOnClickListener(v -> {
            Intent intent = new Intent(proses_ambil.this, pesanan_selesai.class);
            startActivity(intent);
        });
    }
}