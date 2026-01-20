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

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Qris.this, pembayaranselesai.class);
            startActivity(intent);
            finish(); // PENTING
        }, 3000);
    }
}
