package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class metodepembayaran extends AppCompatActivity {

    RadioGroup rgPembayaran;
    Button btnPilih;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);

        rgPembayaran = findViewById(R.id.rgPembayaran);
        btnPilih = findViewById(R.id.btnPilih);
        btnBack = findViewById(R.id.btnBack);

        // tombol back
        btnBack.setOnClickListener(view -> {
            finish();
        });

        // tombol pilih metode
        btnPilih.setOnClickListener(view -> {
            if (rgPembayaran.getCheckedRadioButtonId() == -1) {
                Toast.makeText(
                        metodepembayaran.this,
                        "Silakan pilih metode pembayaran",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                startActivity(new Intent(
                        metodepembayaran.this,
                        Qris.class
                ));

            }
        });
    }
}
