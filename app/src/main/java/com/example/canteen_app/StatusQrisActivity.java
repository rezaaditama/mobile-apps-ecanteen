package com.example.canteen_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StatusQrisActivity extends AppCompatActivity {

    private ImageView backQrisAktif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_status_qris);

        backQrisAktif   = findViewById(R.id.backQris);

        backQrisAktif.setOnClickListener(v -> {
            Intent intent = new Intent(StatusQrisActivity.this, PesananAktifActivity.class);
            startActivity(intent);
        });
    }
}