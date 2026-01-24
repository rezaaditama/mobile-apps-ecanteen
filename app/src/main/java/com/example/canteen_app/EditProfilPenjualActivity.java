package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfilPenjualActivity extends AppCompatActivity {
    private ImageButton backEditProfilPenjual;
    private TextView cancelEditProfilPenjual;
    private Button simpanEditProfilPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profil_penjual);

        backEditProfilPenjual      = findViewById(R.id.img_back_edit_profil_penjual);
        cancelEditProfilPenjual    = findViewById(R.id.tv_cancel_edit_profil_penjual);
        simpanEditProfilPenjual    = findViewById(R.id.btn_simpan_edit_profil_penjual);

        backEditProfilPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfilPenjualActivity.this, ProfilPenjualActivity.class);
            startActivity(intent);
        });

        cancelEditProfilPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfilPenjualActivity.this, ProfilPenjualActivity.class);
            startActivity(intent);
        });

        simpanEditProfilPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfilPenjualActivity.this, ProfilPenjualActivity.class);
            startActivity(intent);
        });
    }
}