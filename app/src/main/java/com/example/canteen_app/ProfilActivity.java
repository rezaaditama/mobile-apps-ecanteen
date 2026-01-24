package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilActivity extends AppCompatActivity {
    private LinearLayout menuEditProfil, btnLogout, menuTentangAplikasi;
    private ImageView btnBack;
    private TextView tvNama, tvEmail, tvTelepon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Inisialisasi
        tvNama = findViewById(R.id.tvPflNama);
        tvEmail = findViewById(R.id.tvPflEmail);
        tvTelepon = findViewById(R.id.tvPflTelepon);
        btnBack = findViewById(R.id.btnBackProfil);
        menuEditProfil = findViewById(R.id.menu_edit_profile);
        menuTentangAplikasi = findViewById(R.id.menuTentangApk);
        btnLogout = findViewById(R.id.btn_logout_profil);

//        Ambil data profile
        displayUserData();

//        Tombol back
        btnBack.setOnClickListener(v -> {
            finish();
        });

//        Edit Profile
        menuEditProfil.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, EditProfilActivity.class);
            startActivity(intent);
        });

        // MENU TENTANG APLIKASI
        menuTentangAplikasi.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, TentangAplikasiActivity.class);
            startActivity(intent);
        });

        // BUTTON LOGOUT
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(ProfilActivity.this, LogoutActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserData();
    }

//    Tampilkan data user
    private void displayUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);

        // Ambil data dengan key yang sama saat Login
        String nama = sharedPreferences.getString("USER_NAMA", "Nama tidak ditemukan");
        String email = sharedPreferences.getString("USER_EMAIL", "Email tidak ditemukan");
        String telp = sharedPreferences.getString("USER_TELP", "-");

        // Set ke TextView
        tvNama.setText(nama);
        tvEmail.setText(email);
        tvTelepon.setText(telp);
    }
}