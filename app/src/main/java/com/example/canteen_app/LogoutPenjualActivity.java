package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogoutPenjualActivity extends AppCompatActivity {
    private Button btnCancelLogoutPenjual, lgtBtnLogoutPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logout_penjual);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Ambil komponen berdasarkan ID
        btnCancelLogoutPenjual = findViewById(R.id.btn_cancel_logout_penjual);
        lgtBtnLogoutPenjual = findViewById(R.id.btn_logout_penjual);

        lgtBtnLogoutPenjual.setOnClickListener(v -> {
//            Hapus data
            SharedPreferences pref = getSharedPreferences("USER_PREF", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.apply();

            Toast.makeText(this, "Berhasil Keluar", Toast.LENGTH_SHORT).show();

//            Pindah ke login
            Intent intent = new Intent(LogoutPenjualActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        btnCancelLogoutPenjual.setOnClickListener(v -> {
            finish();
        });
    }
}