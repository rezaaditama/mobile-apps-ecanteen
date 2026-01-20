package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

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

//            Simpan data
            Order orderBaru = new Order(idOrder, CartManager.getInstance().getCartList(), jam, total, metode);
            CartManager.getInstance().addOrderToHistory(orderBaru);

//          Pindah dengan membawa data
            Intent intent = new Intent(Qris.this, pembayaranselesai.class);
            intent.putExtra("ID", idOrder);
            intent.putExtra("TOTAL", total);
            intent.putExtra("METODE", metode);
            intent.putExtra("JAM", jam);

//            Pindah halaman
            startActivity(intent);

//            Bersihkan keranjang
            CartManager.getInstance().clearCart();
            finish();
        }, 3000);
    }
}
