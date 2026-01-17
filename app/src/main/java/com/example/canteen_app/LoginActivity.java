package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private Button btnpenjual, btnpembeli, btnmasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Button Color Active
        btnpembeli = findViewById(R.id.btn_pembeli);
        btnpenjual = findViewById(R.id.btn_penjual);
        btnmasuk = findViewById(R.id.btn_masuk);

        // 1. Set kondisi awal (Misal login yang aktif)
        btnpembeli.setSelected(true);
        btnpenjual.setSelected(false);

        // 2. Klik Login
        btnpembeli.setOnClickListener(v -> {
            btnpembeli.setSelected(true);
            btnpenjual.setSelected(false);
            // Tambahkan perintah pindah fragment atau lainnya
        });

        // 3. Klik Register
        btnpenjual.setOnClickListener(v -> {
            btnpenjual.setSelected(true);
            btnpembeli.setSelected(false);
            // Tambahkan perintah pindah fragment atau lainnya
        });

        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });
    }
}
