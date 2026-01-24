package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EditMenu1Penjual extends AppCompatActivity {
    private ImageView btnBackEditMenu1;
    private TextView tvCancelEditMenu1;
    private Button btnSimpanEditMenu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_menu1_penjual);

        btnBackEditMenu1    = findViewById(R.id.btnBack_edit_menu1_penjual);
        tvCancelEditMenu1   = findViewById(R.id.tvBatal_edit_menu1_penjual);
        btnSimpanEditMenu1  = findViewById(R.id.btnSimpan_edit_menu1_penjual);

        //Button Back
        btnBackEditMenu1.setOnClickListener(v -> {
            Intent intent = new Intent(EditMenu1Penjual.this, MenuPenjual.class);
            startActivity(intent);
        });

        //TextView Cancel
        tvCancelEditMenu1.setOnClickListener(v -> {
            Intent intent = new Intent(EditMenu1Penjual.this, MenuPenjual.class);
            startActivity(intent);
        });

        //Button Simpan
        btnSimpanEditMenu1.setOnClickListener(v -> {
            Intent intent = new Intent(EditMenu1Penjual.this, MenuPenjual.class);
            startActivity(intent);
        });
    }
}