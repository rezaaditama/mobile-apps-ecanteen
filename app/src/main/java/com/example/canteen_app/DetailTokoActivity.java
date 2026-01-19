package com.example.canteen_app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailTokoActivity extends AppCompatActivity implements CardListener{

//    Inisialisasi
    private RecyclerView rvMenu;
    private TextView tvTotalPrice;
    private MenuAdapter adapter;
    private List<Menu> listDataMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_toko);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mengambil komponen berdasarkan id
        rvMenu = findViewById(R.id.rv_daftar_toko);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // Dummy Data
        listDataMenu = new ArrayList<>();
        listDataMenu.add(new Menu("Nasi Goreng Spesial", 15000, R.drawable.ic_user_avatar));
        listDataMenu.add(new Menu("Mie Ayam Bakso", 12000, R.drawable.ic_user_avatar));
        listDataMenu.add(new Menu("Ayam Geprek", 13000, R.drawable.ic_user_avatar));
        listDataMenu.add(new Menu("Es Teh Manis", 5000, R.drawable.ic_user_avatar));

//        Set Adapter
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuAdapter(this, listDataMenu, this);
        rvMenu.setAdapter(adapter);
    }

//        Count
        @Override
        public void onTotalChanged(int newTotal) {
            tvTotalPrice.setText("Rp " + String.format("%,d", newTotal).replace(',', '.'));
        }
}