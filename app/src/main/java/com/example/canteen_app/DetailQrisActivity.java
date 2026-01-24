package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DetailQrisActivity extends AppCompatActivity {

    private ImageView btnQris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_qris);

       btnQris  = findViewById(R.id.btnBack_Qris);

        btnQris.setOnClickListener(v -> {
            Intent intent = new Intent(DetailQrisActivity.this, LaporanPenjualanActivity.class);
            startActivity(intent);
        });

    }
}