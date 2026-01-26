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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilPenjualActivity extends AppCompatActivity {
    private ImageButton backEditProfilPenjual;
    private TextView cancelEditProfilPenjual, tvNamaTokoHeader;
    private EditText etNamaToko, etNamaPenjual, etEmail, etNoTelp;
    private Button simpanEditProfilPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profil_penjual);

//        Inisialisasi
        initViews();

//        Ambil data penjual
        loadCurrentData();

//        Logika kembali
        backEditProfilPenjual.setOnClickListener(v -> finish());
        cancelEditProfilPenjual.setOnClickListener(v -> finish());

//        LOgika SImpan
        simpanEditProfilPenjual.setOnClickListener(v -> {
            updateDataKeServer();
        });
    }

    //    Tangkap komponen berdasarkan ID
    private void initViews() {
        backEditProfilPenjual = findViewById(R.id.img_back_edit_profil_penjual);
        cancelEditProfilPenjual = findViewById(R.id.tv_cancel_edit_profil_penjual);
        simpanEditProfilPenjual = findViewById(R.id.btn_simpan_edit_profil_penjual);
        tvNamaTokoHeader = findViewById(R.id.tv2_edit_profil_penjual);
        etNamaToko = findViewById(R.id.edt1_edit_profil_penjual);
        etNamaPenjual = findViewById(R.id.edt2_edit_profil_penjual);
        etEmail = findViewById(R.id.edt3_edit_profil_penjual);
        etNoTelp = findViewById(R.id.edt4_edit_profil_penjual);
    }

    private void loadCurrentData() {
        SharedPreferences pref = getSharedPreferences("USER_PREF", MODE_PRIVATE);

        String namaToko = pref.getString("USER_TOKO", "");
        etNamaToko.setText(namaToko);
        tvNamaTokoHeader.setText(namaToko);

        etNamaPenjual.setText(pref.getString("USER_NAMA", ""));
        etEmail.setText(pref.getString("USER_EMAIL", ""));
        etNoTelp.setText(pref.getString("USER_TELP", ""));
    }

    private void updateDataKeServer() {
//        Ambil data penjual
        SharedPreferences pref = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        int userId = pref.getInt("USER_ID", -1);

//        Manipulasi komponen
        String namaToko = etNamaToko.getText().toString().trim();
        String namaPenjual = etNamaPenjual.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String noTelp = etNoTelp.getText().toString().trim();

//        Validasi Input
        if (namaToko.isEmpty() || namaPenjual.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Mohon isi field yang wajib", Toast.LENGTH_SHORT).show();
            return;
        }

        RetrofitClient.getApiService().updateProfilePenjual(userId, namaPenjual, email, noTelp, namaToko).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("USER_NAMA", namaPenjual);
                    editor.putString("USER_EMAIL", email);
                    editor.putString("USER_TELP", noTelp);
                    editor.putString("USER_TOKO", namaToko);
                    editor.apply();

                    Toast.makeText(EditProfilPenjualActivity.this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();

                    finish();
            } else {
                    Toast.makeText(EditProfilPenjualActivity.this, "Gagal update: " + response.message(), Toast.LENGTH_SHORT).show();
                }
        }
            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                Toast.makeText(EditProfilPenjualActivity.this, "Kesalahan jaringan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}