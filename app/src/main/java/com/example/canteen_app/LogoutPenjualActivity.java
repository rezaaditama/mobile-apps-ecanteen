package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

        btnCancelLogoutPenjual = findViewById(R.id.btn_cancel_logout_penjual);
        lgtBtnLogoutPenjual = findViewById(R.id.btn_logout_penjual);

        lgtBtnLogoutPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(LogoutPenjualActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btnCancelLogoutPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(LogoutPenjualActivity.this, ProfilPenjualActivity.class);
            startActivity(intent);
        });
    }
}