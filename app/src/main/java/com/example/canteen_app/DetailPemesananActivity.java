package com.example.canteen_app;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DetailPemesananActivity extends AppCompatActivity {

    private ImageView btnCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_pemesanan);

        btnCod  = findViewById(R.id.btnBack_Cod);


        btnCod.setOnClickListener(v -> {
            Intent intent = new Intent(DetailPemesananActivity.this, LaporanPenjualanActivity.class);
            startActivity(intent);
        });
    }
}