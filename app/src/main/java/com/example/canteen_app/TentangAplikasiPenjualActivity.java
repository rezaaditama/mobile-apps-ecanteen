package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TentangAplikasiPenjualActivity extends AppCompatActivity {
    private ImageButton backTentangApkPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tentang_aplikasi_penjual);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backTentangApkPenjual = findViewById(R.id.img_back_tentang_apk_penjual);

        backTentangApkPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(TentangAplikasiPenjualActivity.this, ProfilPenjualActivity.class);
            startActivity(intent);
        });
    }
}