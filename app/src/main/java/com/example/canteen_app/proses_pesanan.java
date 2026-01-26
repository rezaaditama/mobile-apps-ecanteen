package com.example.canteen_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class proses_pesanan extends AppCompatActivity {
    //    Inisialisasi Komponen
    private View lineSelesai;
    private FrameLayout circleSelesai;
    private Button btnAction;
    private TextView tvNamaMenu, tvTotalHarga, tvOrderId, tvCatatan;
    private String orderId;
    private boolean isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proses_pesanan);

        // Ambil komponen berdasarkan ID
        lineSelesai = findViewById(R.id.line_selesai);
        circleSelesai = findViewById(R.id.circle_selesai);
        btnAction = findViewById(R.id.btnActionProses);
        tvNamaMenu = findViewById(R.id.tv_nama_menu_detail);
        tvTotalHarga = findViewById(R.id.tv_total_harga_detail);
        tvOrderId = findViewById(R.id.tv_order_id_detail);
        tvCatatan = findViewById(R.id.tv_catatan_detail);

//        Ambil ID pesanan
        orderId = getIntent().getStringExtra("ORDER_ID");
        tvOrderId.setText("Id: #" + orderId);


//        Ambil data dari database
        fetchDataOrderDetail();

        // Logika Klik Tombol
        btnAction.setOnClickListener(v -> {
            if (!isFinished) {
                // UPDATE KE DATABASE
                updateStatusSelesai();
            } else {
                finish();
            }
        });
        findViewById(R.id.btnBackProses).setOnClickListener(v -> finish());
    }

    private void fetchDataOrderDetail() {
        RetrofitClient.getApiService().getOrderDetail(orderId).enqueue(new Callback<BerandaPenjualResponse.OrderModel>() {
            @Override
            public void onResponse(Call<BerandaPenjualResponse.OrderModel> call, Response<BerandaPenjualResponse.OrderModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BerandaPenjualResponse.OrderModel data = response.body();
                    tvNamaMenu.setText(data.getRincianMenu());
                    tvCatatan.setText("Catatan: " + data.getNote());
                    tvTotalHarga.setText(String.format("%,d", data.getTotalHarga()));
                }
            }

            @Override
            public void onFailure(Call<BerandaPenjualResponse.OrderModel> call, Throwable t) {
                Toast.makeText(proses_pesanan.this, "Gagal memuat detail", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void updateStatusSelesai() {
        btnAction.setEnabled(false);
        RetrofitClient.getApiService().updateStatusSelesai(orderId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

//                Kalau sukses
                if (response.isSuccessful()) {
                    lineSelesai.setBackgroundColor(ContextCompat.getColor(proses_pesanan.this, R.color.orange));
                    circleSelesai.setBackgroundResource(R.drawable.circle2);

//                    Set tombol kembali
                    btnAction.setText("KEMBALI KE BERANDA");
                    btnAction.setEnabled(true);
                    isFinished = true;

                    Toast.makeText(proses_pesanan.this, "Pesanan berhasil diselesaikan!", Toast.LENGTH_SHORT).show();
                }
            }

            //            Kalau gagal
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                btnAction.setEnabled(true);
                Toast.makeText(proses_pesanan.this, "Koneksi gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}