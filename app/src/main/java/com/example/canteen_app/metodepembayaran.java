package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
//                Metode Pembayaran
                String metode = (selectedId == R.id.rbQris) ? "QRIS" : "Tunai";
//                Data jam
                String jamAmbil = getIntent().getStringExtra("PICKUP_TIME");

//                Pindah Halaman ke qris
                Intent intent = new Intent(metodepembayaran.this, Qris.class);
                intent.putExtra("METODE_BAYAR", metode);
                intent.putExtra("PICKUP_TIME", jamAmbil);
                startActivity(intent);
            }
        });
    }
}
