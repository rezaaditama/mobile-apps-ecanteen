package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {
    private ImageButton backEditProfil;
    private TextView cancelEditProfil;
    private Button simpanEditProfil;
    private EditText etNama, etEmail, etTelepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Ambil komponen berdasarkan ID
        backEditProfil = findViewById(R.id.imgEdtPflArrowLeft);
        cancelEditProfil = findViewById(R.id.tvEdtPflCancel);
        simpanEditProfil = findViewById(R.id.btnSimpanEditProfil);
        etNama = findViewById(R.id.edtPflEditTextNamaPanjang);
        etEmail = findViewById(R.id.edtPflEditTextEmail);
        etTelepon = findViewById(R.id.edtPflEditTextNomorTelepon);

//        Tampilkan data saat ini
        SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        etNama.setText(sharedPreferences.getString("USER_NAMA", ""));
        etEmail.setText(sharedPreferences.getString("USER_EMAIL", ""));
        etTelepon.setText(sharedPreferences.getString("USER_TELP", ""));

//        Tombol cancel
        backEditProfil.setOnClickListener(v -> finish());
        cancelEditProfil.setOnClickListener(v -> finish());

//        Tombol simpan
        simpanEditProfil.setOnClickListener(v -> {
            updateData();
        });
    }

//        Update data
        private void updateData() {
            SharedPreferences sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE);
            int userId = sharedPref.getInt("USER_ID", 0);
            String nama = etNama.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String telepon = etTelepon.getText().toString().trim();

//            Validasi
            if (nama.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Nama dan Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

//            API
            ApiService apiService = RetrofitClient.getApiService();
            apiService.updateUser(userId, nama, email, telepon).enqueue(new Callback<UpdateResponse>() {
                @Override
                public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
//                    Update berhasil
                    if (response.isSuccessful()) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("USER_NAMA", nama);
                        editor.putString("USER_EMAIL", email);
                        editor.putString("USER_TELP", telepon);
                        editor.apply();

                        Toast.makeText(EditProfilActivity.this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
                        finish(); // Kembali ke ProfilActivity
                    }
                }
                @Override
                public void onFailure(Call<UpdateResponse> call, Throwable t) {
                    Toast.makeText(EditProfilActivity.this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }