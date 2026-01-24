package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilPenjualActivity extends AppCompatActivity {
    private LinearLayout menuEditProfilPenjual, btnLogoutPenjual, menuTentangAplikasiPenjual, navhome, navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil_penjual);
        navhome     = findViewById(R.id.menu_home);
        navMenu     = findViewById(R.id.menu_penjual);

        menuEditProfilPenjual = findViewById(R.id.menu_edit_profil_profil_penjual);
        menuEditProfilPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, EditProfilPenjualActivity.class);
            startActivity(intent);
        });

        // MENU TENTANG APLIKASI
        menuTentangAplikasiPenjual = findViewById(R.id.menuTentangApkPenjual);
        menuTentangAplikasiPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, TentangAplikasiPenjualActivity.class);
            startActivity(intent);
        });

        // BUTTON LOGOUT
        btnLogoutPenjual = findViewById(R.id.btn_logout_profil_penjual);
        btnLogoutPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, LogoutPenjualActivity.class);
            startActivity(intent);

        });

        //navButton Menu Beranda
        navhome.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, BerandaPenjualActivity.class);
            startActivity(intent);
        });

        //navButton Menu Penjualan
        navMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, MenuPenjual.class);
            startActivity(intent);
        });
    }
}