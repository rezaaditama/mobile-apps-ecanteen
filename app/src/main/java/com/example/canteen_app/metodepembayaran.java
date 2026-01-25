package com.example.canteen_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class metodepembayaran extends AppCompatActivity {
    //    Deklarasi Variabel
    RadioGroup rgPembayaran;
    Button btnPilih;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);

//        Tangkap Komponen berdasarkan id
        rgPembayaran = findViewById(R.id.rgPembayaran);
        btnPilih = findViewById(R.id.btnPilih);
        btnBack = findViewById(R.id.btnBack);

        // tombol back
        btnBack.setOnClickListener(view -> {
            finish();
        });

        // tombol pilih metode
        btnPilih.setOnClickListener(view -> {
            int selectedId = rgPembayaran.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(
                        metodepembayaran.this,
                        "Silakan pilih metode pembayaran",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
//                Metode Pembayaran dan jam pengambilan
                String metode = (selectedId == R.id.rbQris) ? "QRIS" : "Tunai";
                String jamAmbil = getIntent().getStringExtra("PICKUP_TIME");

            // Jalankan proses order
                prosesSimpanPesanan(metode, jamAmbil);
            }
        });
    }

    // Untuk pembayaran cash
    private void prosesSimpanPesanan(String metode, String jam) {
//        Ambil user dari sharedPreferences
        android.content.SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", 1);

        // Ambil data dari keranjang
        List<Menu> keranjangSekarang = CartManager.getInstance().getCartList();
        if (keranjangSekarang.isEmpty()) {
            Toast.makeText(this, "Keranjang kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

//        Buat 1 order ID utama
        String masterOrderId = "INV-" + System.currentTimeMillis();
        int totalHargaSemua = 0;
        for (Menu item : keranjangSekarang) {
            totalHargaSemua += (item.getProductPrice() * item.getQty());
        }

//        Buat object untuk dikirim ke API
        Order orderBaru = new Order(masterOrderId, keranjangSekarang, jam, totalHargaSemua, metode, userId);

//        Kirim ke server
        kirimPesananKeServer(orderBaru);
    }

    private void kirimPesananKeServer(Order order) {
//        API
        ApiService apiService = RetrofitClient.getApiService();
        apiService.simpanPesanan(order).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {

//                Kalau response berhasil
                if (response.isSuccessful() && response.body() != null) {

//                    Redirect ke pembayaran
                    if (order.getPaymentMethod().equalsIgnoreCase("QRIS")) {

//                        Mengambil URL
                        String redirectUrl = response.body().getRedirectUrl();

                        if (redirectUrl != null && !redirectUrl.isEmpty()) {

                            // Bersihkan keranjang & Simpan ke history lokal dulu
                            CartManager.getInstance().addOrderToHistory(order);
                            CartManager.getInstance().clearCart();

//                            Siapkan halaman pesanan aktif
                            Intent intentPesanan = new Intent(metodepembayaran.this, PesananAktifActivity.class);
                            intentPesanan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

//                       siapkan Buka Browser
                            Intent intentBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(redirectUrl));
                            intentBrowser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(intentPesanan);
                            startActivity(intentBrowser);

                            Toast.makeText(metodepembayaran.this, "Selesaikan pembayaran di browser", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    } else {
                        aksiSetelahBerhasil(order);
                        }
                    } else {
                    Toast.makeText(metodepembayaran.this, "Server error: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

//            Kalau API error
            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.e("API_ERROR", "Gagal kirim: " + t.getMessage());
                Toast.makeText(metodepembayaran.this, "Gagal terhubung ke server. Periksa internet Anda.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void aksiSetelahBerhasil(Order order) {
        // Simpan ke riwayat lokal aplikasi
        CartManager.getInstance().addOrderToHistory(order);

        // Bersihkan keranjang
        CartManager.getInstance().clearCart();

        // Pindah ke halaman Pesanan Aktif
        Intent intent = new Intent(metodepembayaran.this, PesananAktifActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        Toast.makeText(this, "Pesanan Berhasil Dibuat!", Toast.LENGTH_LONG).show();
        finish();
    }
}