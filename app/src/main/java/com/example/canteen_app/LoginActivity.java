package com.example.canteen_app;

//import android.os.Bundle;
//
//import android.telecom.Call;
//import android.widget.Button;
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import android.content.Intent;
//import android.graphics.Color;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.text.method.LinkMovementMethod;
//import android.text.style.ClickableSpan;
//import android.text.style.ForegroundColorSpan;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.HashMap;
//
//import okhttp3.Response;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnpenjual, btnpembeli, btnMasuk;
    private EditText etEmail, etPassword;
    private TextView tvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

//        Inisialisasi View
        btnpembeli = findViewById(R.id.btn_pembeli);
        btnpenjual = findViewById(R.id.btn_penjual);
        btnMasuk = findViewById(R.id.btn_masuk);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvDaftar = findViewById(R.id.tv_daftar);

//        Kondisi awal button active
        btnpembeli.setSelected(true);
        btnpenjual.setSelected(false);

//        Trigger tombol pembeli
        btnpembeli.setOnClickListener(v -> {
            btnpembeli.setSelected(true);
            btnpenjual.setSelected(false);
        });

//       trigger tombol penjual
        btnpenjual.setOnClickListener(v -> {
            btnpenjual.setSelected(true);
            btnpembeli.setSelected(false);
        });

//Handle login
        btnMasuk.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua data", Toast.LENGTH_SHORT);
                return;
            }
            prosessLogin(email, password);
        });
    }

//    Method untuk HIT API Auth Login
    private void prosessLogin(String email, String password) {

//        Inisialisasi data sesuai data backend
        HashMap<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

//        HIT API
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.loginUser(body);

//        Konfigurasi Request
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
//                Apabila Login Berhasil
                    String nameUser = response.body().getData().getFullname();
                    Toast.makeText(LoginActivity.this, "Selamat Datang" + nameUser, Toast.LENGTH_SHORT).show();

//                    Pindah ke MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); //Tutup halaman login
                } else {
//                    Login Gagal
                    Toast.makeText(LoginActivity.this, "Email atau password salah!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
//                Apabila server mati
                Toast.makeText(LoginActivity.this, "Kesalahan server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

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