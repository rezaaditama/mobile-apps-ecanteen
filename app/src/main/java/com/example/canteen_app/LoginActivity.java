package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnPembeli, btnPenjual, btnMasuk;
    private TextView tvDaftar;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // INIT VIEW
        btnPembeli = findViewById(R.id.btn_pembeli);
        btnPenjual = findViewById(R.id.btn_penjual);
        tvDaftar = findViewById(R.id.tv_daftar);
        btnMasuk = findViewById(R.id.btn_masuk);
        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);

        // underline "Daftar"
        tvDaftar.setPaintFlags(tvDaftar.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // DEFAULT: Pembeli aktif
        btnPembeli.setSelected(true);
        btnPenjual.setSelected(false);

        // Klik Pembeli
        btnPembeli.setOnClickListener(v -> {
            btnPembeli.setSelected(true);
            btnPenjual.setSelected(false);
        });

        // Klik Penjual
        btnPenjual.setOnClickListener(v -> {
            btnPenjual.setSelected(true);
            btnPembeli.setSelected(false);
        });

        // Klik Daftar → Register
        tvDaftar.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, Register.class));
        });

        // LOGIKA LOGIN KE BACKEND
        btnMasuk.setOnClickListener(v -> {
            performLogin();
        });
    }

    //    Login ke backend
    private void performLogin() {
//        Ambil data
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String role = btnPembeli.isSelected() ? "pembeli" : "penjual";

//        Validasi input kosong
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

//        Siapkan request
        LoginRequest loginRequest = new LoginRequest(email, password, role);

//        HIT API
        RetrofitClient.getApiService().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Simpan data user ke SharedPreferences
                    LoginResponse loginResponse = response.body();
                    saveUserSession(
                            loginResponse.getUserId(),
                            loginResponse.getNama(),
                            loginResponse.getEmail(),
                            loginResponse.getNomorTelepon(),
                            role
                    );

                    Toast.makeText(LoginActivity.this, "Login Berhasil! Halo " + loginResponse.getNama(), Toast.LENGTH_SHORT).show();

//                    Filter berdasarkan rola
                    Intent intent;
                    if (role.equalsIgnoreCase("penjual")) {
                        // Jika role penjual, arahkan ke BerandaPenjualActivity
                        intent = new Intent(LoginActivity.this, BerandaPenjualActivity.class);
                    } else {
                        // Jika role pembeli, arahkan ke BerandaActivity
                        intent = new Intent(LoginActivity.this, BerandaActivity.class);
                    }

                    startActivity(intent);
                    finish(); // Hapus LoginActivity dari tumpukan (backstack)
                } else {
                    Toast.makeText(LoginActivity.this, "Email, Password, atau Role salah!", Toast.LENGTH_SHORT).show();
                }
            }

            //            Kalau HIT gagal
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOGIN_ERROR", "Error: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Gagal terhubung ke server. Cek koneksi/IP Backend.", Toast.LENGTH_LONG).show();
            }
        });
    }

    //    Simpan sesi user
    private void saveUserSession(int userId, String nama, String email, String telepon, String role) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("USER_ID", userId);
        editor.putString("USER_NAMA", nama);
        editor.putString("USER_EMAIL", email);
        editor.putString("USER_TELP", telepon);
        editor.putString("USER_ROLE", role);
        editor.putBoolean("IS_LOGGED_IN", true);

        editor.apply();
    }
}
