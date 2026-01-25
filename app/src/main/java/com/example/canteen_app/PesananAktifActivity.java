package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananAktifActivity extends AppCompatActivity {
    //    Mendeklarasikan variabel
    private RecyclerView rvPesanan;
    private View indicatorAktif, indicatorSelesai;
    private TextView tvAktif, tvSelesai, tvEmpty;
    private PesananAdapter adapter;
    private boolean currentTabIsSelesai = false;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_aktif);

//        Manipulasi tombol kembali
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                goToBeranda();
            }
        });

//        Tangkap komponen berdasarkan ID
        rvPesanan = findViewById(R.id.rvPesanan);
        indicatorAktif = findViewById(R.id.indicatorAktif);
        indicatorSelesai = findViewById(R.id.indicatorSelesai);
        tvAktif = findViewById(R.id.tvAktif);
        tvSelesai = findViewById(R.id.tvSelesai);
        tvEmpty = findViewById(R.id.tvEmptyState);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        rvPesanan.setLayoutManager(new LinearLayoutManager(this));

        // Memanggil ulang data dari server saat ditarik
        swipeRefresh.setOnRefreshListener(() -> {
            ambilDataDariServer(currentTabIsSelesai);
        });

//        Set loading
        swipeRefresh.setColorSchemeResources(R.color.primary, android.R.color.holo_blue_dark);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() { goToBeranda(); }
        });

        // Tombol Kembali
        findViewById(R.id.imgKrjArrowLeft).setOnClickListener(v -> goToBeranda());

//        Listener tab
        findViewById(R.id.tabAktif).setOnClickListener(v -> {
            currentTabIsSelesai = false;
            showTabContent(false);
        });
        findViewById(R.id.tabSelesai).setOnClickListener(v -> {
            currentTabIsSelesai = true;
            showTabContent(true);
        });
        showTabContent(false);
    }

//    Fungsi tombol kembali
private void goToBeranda() {
    Intent intent = new Intent(PesananAktifActivity.this, BerandaActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    startActivity(intent);
    finish();
}

    //    Kalau dari halaman lain mau ke beranda
    @Override
    protected void onResume() {
        super.onResume();
        showTabContent(currentTabIsSelesai);
    }

    private void showTabContent(boolean isSelesai) {
        if (!isSelesai) {
//            UI kalau aktif
            indicatorAktif.setVisibility(View.VISIBLE);
            indicatorSelesai.setVisibility(View.INVISIBLE);
            tvAktif.setTextColor(getResources().getColor(R.color.primary));
            tvSelesai.setTextColor(Color.GRAY);
        } else {
//            UI kalau selesai
            indicatorAktif.setVisibility(View.INVISIBLE);
            indicatorSelesai.setVisibility(View.VISIBLE);
            tvSelesai.setTextColor(getResources().getColor(R.color.primary));
            tvAktif.setTextColor(Color.GRAY);
        }

        // Ambil data dari API
        ambilDataDariServer(isSelesai);
    }

    private void ambilDataDariServer(boolean statusSelesai) {
        // Mulai animasi loading
        swipeRefresh.setRefreshing(true);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", 1);

        ApiService apiService = RetrofitClient.getApiService();
        // Memanggil endpoint: GET /api/orders/user/:user_id
        apiService.getOrdersByUser(userId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                // Berhentikan animasi loading
                swipeRefresh.setRefreshing(false);

//                Ambil data kalau response nya ada
                if (response.isSuccessful() && response.body() != null) {
                    List<Menu> listTampil = new ArrayList<>();

                for (Order order : response.body()) {
//                    Cek midtrans
                    String statusMidtrans = order.getStatusPembayaran();
                    boolean isFailed = "cancel".equalsIgnoreCase(statusMidtrans) ||
                            "expire".equalsIgnoreCase(statusMidtrans) ||
                            "deny".equalsIgnoreCase(statusMidtrans);

                    boolean shouldShow;
                    if (statusSelesai) {
                        shouldShow = order.isFinished() || isFailed;
                    } else {
                        shouldShow = !order.isFinished() && !isFailed;
                    }

                    // Cek apakah status is_finished dari server sesuai dengan tab yang dipilih
                    if (shouldShow) {
                        for (Menu item : order.getItems()) {
                            item.convertPathToResourceId(PesananAktifActivity.this);
                            item.setParentOrderId(order.getOrderId());
                            item.setParentPickupTime(order.getPickupTime());
                            item.setPaymentMethod(order.getPaymentMethod());
                            item.setStatusPembayaran(order.getStatusPembayaran());
                            listTampil.add(item);
                        }
                    }
                }
                    updateUI(listTampil, statusSelesai);
                } else {
                    Log.e("API_STATUS", "Respon gagal atau body kosong");
                }
            }

//        Kalau gagal
        @Override
        public void onFailure(Call<List<Order>> call, Throwable t) {
            Log.e("API_ERROR", "Gagal load pesanan: " + t.getMessage());
            Toast.makeText(PesananAktifActivity.this, "Gagal konek ke server", Toast.LENGTH_SHORT).show();
        }
        });
    }

//    Update UI
    private void updateUI(List<Menu> list, boolean statusSelesai) {
        adapter = new PesananAdapter(this, list);
        rvPesanan.setAdapter(adapter);

//        Kalau pesanan kosong
        if (list.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            tvEmpty.setText(statusSelesai ? "Belum ada pesanan selesai" : "Tidak ada pesanan aktif");
        } else {
            tvEmpty.setVisibility(View.GONE);
        }
    }
}