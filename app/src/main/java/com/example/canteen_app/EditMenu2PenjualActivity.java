package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class EditMenu2PenjualActivity extends AppCompatActivity {
    private ImageView btnBackEditMenu2;
    private TextView tvCancelEditMenu2;
    private Button btnSimpanEditMenu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_menu2_penjual);

        btnBackEditMenu2    = findViewById(R.id.btnBack_edit_menu2_penjual);
        tvCancelEditMenu2   = findViewById(R.id.tvBatal_edit_menu2_penjual);
        btnSimpanEditMenu2  = findViewById(R.id.btnSimpan_edit_menu2_penjual);

        //Button Back
        btnBackEditMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(EditMenu2PenjualActivity.this, MenuPenjual.class);
            startActivity(intent);
        });

        //TextView Cancel
        tvCancelEditMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(EditMenu2PenjualActivity.this, MenuPenjual.class);
            startActivity(intent);
        });

        //Button Simpan
        btnSimpanEditMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(EditMenu2PenjualActivity.this, MenuPenjual.class);
            startActivity(intent);
        });
    }
}