package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Qris extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qris);

//        Persiapan Delay
        new Handler().postDelayed(() -> {
//            Ambil Data Dari Halaman Sebelumnya
            String metode = getIntent().getStringExtra("METODE_BAYAR");
            String jam = getIntent().getStringExtra("PICKUP_TIME");
            int total = CartManager.getInstance().getGlobalTotal();
            String idOrder = "ORD-" + System.currentTimeMillis();

//          Ambil menu dari keranjang
            List<Menu> keranjangSekarang = CartManager.getInstance().getCartList();

//            Pecah jadi per item
            for (Menu m : keranjangSekarang) {
                m.setParentOrderId(idOrder);
                m.setPaymentMethod(metode);
                m.setParentPickupTime(jam);
            }

//          copy data
            List<Menu> copyUntukHistory = new ArrayList<>(keranjangSekarang);

//            Simpan data
            Order orderBaru = new Order(idOrder, copyUntukHistory, jam, total, metode);
            CartManager.getInstance().addOrderToHistory(orderBaru);

//            Bersihkan keranjang
            CartManager.getInstance().clearCart();

//          Pindah dengan membawa data
            Intent intent = new Intent(Qris.this, pembayaranselesai.class);
            intent.putExtra("ID", idOrder);
            intent.putExtra("TOTAL", total);
            intent.putExtra("METODE", metode);
            intent.putExtra("JAM", jam);

//            Pindah halaman
            startActivity(intent);

            finish();
        }, 3000);
    }
}
