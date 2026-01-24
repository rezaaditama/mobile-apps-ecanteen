package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPenjual extends AppCompatActivity {
    private Button btnEditMenu1, btnEditMenu2 ;
    private Switch switchMenu1, switchMenu2;
    private TextView tvStatusSwitch1, tvStatusSwitch2;
    private LinearLayout navHome,navPenjual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_penjual);

        btnEditMenu1    = findViewById(R.id.btn_menu_penjual_edit_menu1);
        btnEditMenu2    = findViewById(R.id. btn_menu_penjual_edit_menu2);
        switchMenu1     = findViewById(R.id.switch_menu_penjual_menu1);
        switchMenu2     = findViewById(R.id.switch_menu_penjual_menu2);
        tvStatusSwitch1 = findViewById(R.id.tv9_menu1_penjual);
        tvStatusSwitch2 = findViewById(R.id.tv12_menu2_penjual);
        navHome         = findViewById(R.id.menu_home);
        navPenjual      = findViewById(R.id.menu_profile);

        //Button Edit Menu 1
        btnEditMenu1.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPenjual.this, EditMenu1Penjual.class);
            startActivity(intent);
        });

        //Button Edit Menu 2
        btnEditMenu2.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPenjual.this, EditMenu2PenjualActivity.class);
            startActivity(intent);
        });

        //Switch Menu 1
        switchMenu1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // KANAN → TERSEDIA
                // lakukan aksi tersedia
                // contoh:
                Toast.makeText(this, "Menu tersedia", Toast.LENGTH_SHORT).show();

            } else {
                // KIRI → TIDAK TERSEDIA
                // lakukan aksi tidak tersedia
                Toast.makeText(this, "Menu tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        });

        //Switch Menu 2
        switchMenu2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // KANAN → TERSEDIA
                // lakukan aksi tersedia
                // contoh:
                Toast.makeText(this, "Menu tersedia", Toast.LENGTH_SHORT).show();

            } else {
                // KIRI → TIDAK TERSEDIA
                // lakukan aksi tidak tersedia
                Toast.makeText(this, "Menu tidak tersedia", Toast.LENGTH_SHORT).show();
            }
        });

        //Teks Tersedia Menu 1
        switchMenu1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvStatusSwitch1.setText("Tersedia");
            } else {
                tvStatusSwitch1.setText("Tidak tersedia");
            }
        });

        //Teks Tersedia Menu 2
        switchMenu2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tvStatusSwitch2.setText("Tersedia");
            } else {
                tvStatusSwitch2.setText("Tidak tersedia");
            }
        });

        //navButton Menu Beranda
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPenjual.this, BerandaPenjualActivity.class);
            startActivity(intent);
        });

        //navButton Menu Beranda
        navPenjual.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPenjual.this, ProfilPenjualActivity.class);
            startActivity(intent);
        });
    }
}