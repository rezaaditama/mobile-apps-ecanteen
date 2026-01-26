package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilPenjualActivity extends AppCompatActivity {
    private LinearLayout menuEditProfilPenjual, btnLogoutPenjual, menuTentangAplikasiPenjual, navhome, navMenu;
    private TextView tvDisplayNamaPenjual, tvDisplayNamaToko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil_penjual);

//        Tangkap komponen berdasarkan ID
        navhome = findViewById(R.id.menu_home);
        navMenu = findViewById(R.id.menu_penjual);
        tvDisplayNamaToko = findViewById(R.id.tv_nama_toko_profil_penjual);
        tvDisplayNamaPenjual = findViewById(R.id.tv_nama_penjual_profil_penjual);
        menuEditProfilPenjual = findViewById(R.id.menu_edit_profil_profil_penjual);
        menuTentangAplikasiPenjual = findViewById(R.id.menuTentangApkPenjual);
        btnLogoutPenjual = findViewById(R.id.btn_logout_profil_penjual);
        btnLogoutPenjual = findViewById(R.id.btn_logout_profil_penjual);

        // Ambil data dari SharedPreferences
        displayDataSession();

//        Button logout
        btnLogoutPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, LogoutPenjualActivity.class);
            startActivity(intent);
        });

//        TOmbol edit profil
        menuEditProfilPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, EditProfilPenjualActivity.class);
            startActivity(intent);
        });

//        TOmbol tentang aplikasi
        menuTentangAplikasiPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, TentangAplikasiPenjualActivity.class);
            startActivity(intent);
        });

//        Nav bottom
        navhome.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, BerandaPenjualActivity.class);
            startActivity(intent);
            finish();
        });

//        Nav menu
        navMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilPenjualActivity.this, MenuPenjual.class);
            startActivity(intent);
            finish();
        });
    }

//    Display data
    private void displayDataSession() {
        SharedPreferences pref = getSharedPreferences("USER_PREF", MODE_PRIVATE);


        String namaPenjual = pref.getString("USER_NAMA", "Nama Penjual");
        String namaToko = pref.getString("USER_TOKO", "Nama Toko");

        tvDisplayNamaPenjual.setText(namaPenjual);
        tvDisplayNamaToko.setText(namaToko);

    }
//        refresh data
        @Override
        protected void onResume() {
            super.onResume();
            displayDataSession();
    }
}