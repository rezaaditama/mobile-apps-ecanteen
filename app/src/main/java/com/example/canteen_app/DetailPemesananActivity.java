package com.example.canteen_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemesananActivity extends AppCompatActivity {
//    Inisialisasi Variabel
    private ImageView btnBack;
    private TextView tvNamaPembeli, tvOrderId, tvTotalHarga, tvRincianMenu, tvNote;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_pemesanan);

//        Ambil ID
        orderId = getIntent().getStringExtra("ORDER_ID");

//      Mengambil komponen berdasarkan ID
        btnBack = findViewById(R.id.btnBack_Cod);
        tvNamaPembeli = findViewById(R.id.tv_nama_pembeli_detail);
        tvOrderId = findViewById(R.id.tv_order_id_detail);
        tvTotalHarga = findViewById(R.id.tv_total_harga_detail);
        tvRincianMenu = findViewById(R.id.tv_rincian_menu);
        tvNote = findViewById(R.id.tv_note_detail);

//        Tombol back
        btnBack.setOnClickListener(v -> finish());

//        Fetch data
        if (orderId != null) {
            fetchDetailPesanan();
        } else {
            Toast.makeText(this, "ID Pesanan tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchDetailPesanan() {
        RetrofitClient.getApiService().getOrderDetail(orderId).enqueue(new Callback<BerandaPenjualResponse.OrderModel>() {
            @Override
            public void onResponse(Call<BerandaPenjualResponse.OrderModel> call, Response<BerandaPenjualResponse.OrderModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BerandaPenjualResponse.OrderModel order = response.body();

                    // Set data hasil API ke UI
                    tvNamaPembeli.setText(order.getNamaPembeli());
                    tvOrderId.setText("#" + order.getOrderId());
                    tvTotalHarga.setText(formatRupiah(order.getTotalHarga()));

                    if (order.getRincianMenu() != null) {
                        tvRincianMenu.setText(order.getRincianMenu());
                    }

                    if (order.getNote() != null) {
                        tvNote.setText("Catatan: " + order.getNote());
                    }

                } else {
                    Toast.makeText(DetailPemesananActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }@Override
            public void onFailure(Call<BerandaPenjualResponse.OrderModel> call, Throwable t) {
                Toast.makeText(DetailPemesananActivity.this, "Gagal memuat detail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String formatRupiah(int number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number).replace(",00", "");
    }
}