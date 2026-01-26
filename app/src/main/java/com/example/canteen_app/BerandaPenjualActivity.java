package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaPenjualActivity extends AppCompatActivity {
    private LinearLayout btnLaporanPenjualan, btnProsesPesanan, btnPesananAktif1;
    private TextView tvLihatSemua, tvNamaWarung, tvNamaPembeli, tvOrderId, tvStatusWarung;
    private TextView tvTotalIncome, tvOrderDoneCount;
    private LinearLayout navMenu, navPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beranda_penjual);

//        Inisialisasi View
        initViews();

//        Load data
        loadDataDashboard();

//        Click Listener
        setupClickListeners();
    }

    //ambil komponen berdasarkan id
    private void initViews() {
        btnLaporanPenjualan = findViewById(R.id.btn_beranda_penjual_laporan_penjualan);
        btnProsesPesanan    = findViewById(R.id.btn_beranda_penjualan_proses_pesanan);
        btnPesananAktif1    = findViewById(R.id.btn_beranda_penjual_pesanan_aktif1);
        tvLihatSemua        = findViewById(R.id.tv8_beranda_penjualan);
        tvNamaWarung        = findViewById(R.id.tv_beranda_penjual_nama_warung);
        tvStatusWarung      = findViewById(R.id.tv_beranda_penjual_status);
        tvNamaPembeli       = findViewById(R.id.tv12_beranda_penjual);
        tvOrderId           = findViewById(R.id.tv13_beranda_penjual);
        tvTotalIncome       = findViewById(R.id.tv4_beranda_penjual);
        tvOrderDoneCount    = findViewById(R.id.tv5_beranda_penjual);
        navMenu             = findViewById(R.id.menu_penjual);
        navPenjual          = findViewById(R.id.menu_profile);
    }

//    Ambil data
    private void loadDataDashboard() {
    //        Ambil data dari SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE);
            int userId = sharedPref.getInt("USER_ID", -1);

            if (userId == -1) {
                Toast.makeText(this, "Sesi berakhir, silakan login kembali", Toast.LENGTH_SHORT).show();
            return;
        }

    //        Ambil API
            RetrofitClient.getApiService().getDashboardPenjual(userId).enqueue(new Callback<BerandaPenjualResponse>() {
                @Override
                public void onResponse(Call<BerandaPenjualResponse> call, Response<BerandaPenjualResponse> response) {

        //            Kalau berhasil
                    if (response.isSuccessful() && response.body() != null) {
                        BerandaPenjualResponse data = response.body();

        //          Update UI
                    if (data.getToko() != null) {
                        String namaToko = data.getToko().getShopName();
                        tvNamaWarung.setText(namaToko);
                        tvStatusWarung.setText("Toko Buka");

//                        Simpan data toko
                        SharedPreferences sharedPref = getSharedPreferences("USER_PREF", MODE_PRIVATE);
                        sharedPref.edit().putString("USER_TOKO", namaToko).apply();
                    }

    //                Update pemasukan
                        tvTotalIncome.setText(formatRupiah(data.getTotalPemasukan()));
                        tvOrderDoneCount.setText(data.getTotalSelesai() + " Pesanan Selesai");

        //            Update UI pesanan aktif
                        List<BerandaPenjualResponse.OrderModel> listOrders = data.getPesananAktif();
                        if (listOrders != null && !listOrders.isEmpty()) {
                            BerandaPenjualResponse.OrderModel orderTerbaru = listOrders.get(0);
                            tvNamaPembeli.setText(orderTerbaru.getNamaPembeli());
                            tvOrderId.setText("#" + orderTerbaru.getOrderId());
                            btnPesananAktif1.setVisibility(View.VISIBLE);
                            findViewById(R.id.tv14_beranda_penjual).setVisibility(View.VISIBLE);
                        } else {
                            tvNamaPembeli.setText("Tidak ada pesanan");
                            tvOrderId.setText("Belum ada pesanan aktif");
                            findViewById(R.id.tv14_beranda_penjual).setVisibility(View.GONE);
                        }
                    }
                }

        //    Kalau gagal
            @Override
            public void onFailure(Call<BerandaPenjualResponse> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                Toast.makeText(BerandaPenjualActivity.this, "Koneksi Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    format rupiah
    private String formatRupiah(double amount) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(amount).replace(",00", "");
    }

//Set on click listener
    private void setupClickListeners() {
        // Laporan Penjualan
        btnLaporanPenjualan.setOnClickListener(v -> startActivity(new Intent(this, LaporanPenjualanActivity.class)));

        // Proses Pesanan & Lihat Semua
        View.OnClickListener keHalamanPesanan = v -> startActivity(new Intent(this, pesanan.class));
        btnProsesPesanan.setOnClickListener(keHalamanPesanan);
        tvLihatSemua.setOnClickListener(keHalamanPesanan);

        btnPesananAktif1.setOnClickListener(v -> {
            String id = tvOrderId.getText().toString().replace("#", "");
            if(!id.contains("Belum")) {
                Intent intent = new Intent(this, proses_pesanan.class);
                intent.putExtra("ORDER_ID", id);
                startActivity(intent);
            }
        });

        navMenu.setOnClickListener(v -> startActivity(new Intent(this, MenuPenjual.class)));
        navPenjual.setOnClickListener(v -> startActivity(new Intent(this, ProfilPenjualActivity.class)));
    }
}