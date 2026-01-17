package com.example.canteen_app;

import android.os.Bundle;

import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    private Button btnpenjual, btnpembeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        // Button Color Active
        btnpembeli = findViewById(R.id.btn_pembeli);
        btnpenjual = findViewById(R.id.btn_penjual);

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



//        Mulai disini ke register
//        // Text Daftar Sekarang
//        TextView tvDaftar = findViewById(R.id.tv_daftar);
//
//        String text = "Belum punya akun? Daftar Sekarang";
//        SpannableString spannableString = new SpannableString(text);
//
//        int start = text.indexOf("Daftar Sekarang");
//        int end = start + "Daftar Sekarang".length();
//
//        // Warna hijau
//        spannableString.setSpan(
//                new ForegroundColorSpan(Color.parseColor("#468f41")),
//                start,
//                end,
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
//        );
//
//        // Klikable
//        spannableString.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        tvDaftar.setText(spannableString);
//        tvDaftar.setMovementMethod(LinkMovementMethod.getInstance());
//        tvDaftar.setHighlightColor(Color.TRANSPARENT);
//    }
//}

    }
}