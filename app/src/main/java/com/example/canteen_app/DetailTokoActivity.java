package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailTokoActivity extends AppCompatActivity implements CardListener {
//    Deklarasi Komponen

    private RecyclerView rvMenu;
    private TextView tvTotalPrice, tvNamaTokoHeader;
    private MenuAdapter adapter;
    private List<Menu> listDataMenu;
    private Button btnCheckout;

    // Variabel untuk menampung data dari Intent
    private int idToko;
    private String namaToko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_toko);

        // Handle Window Insets (Agar layout tidak tertutup status bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi View berdasarkan id
        rvMenu = findViewById(R.id.rv_daftar_toko);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        tvNamaTokoHeader = findViewById(R.id.tv_nama_toko); // Pastikan ID ini ada di XML
        btnCheckout = findViewById(R.id.btn_checkout);    // Pastikan ID ini ada di XML

//        Tombol back
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // Tangkap Data dari Intent (TokoAdapter)
        idToko = getIntent().getIntExtra("ID_TOKO", 0);
        namaToko = getIntent().getStringExtra("NAMA_TOKO");

        // Set nama toko di header
        if (namaToko != null) {
            tvNamaTokoHeader.setText(namaToko);
        }

        // Siapkan Data Menu (Filter berdasarkan idToko)
        listDataMenu = new ArrayList<>();
        populateMenuData();

        // Set Adapter
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuAdapter(this, listDataMenu, this);
        rvMenu.setAdapter(adapter);

        // 5. Logika Tombol Checkout
        btnCheckout.setOnClickListener(v -> {
            ArrayList<Menu> keranjang = new ArrayList<>();
            for (Menu m : listDataMenu) {
                if (m.getQty() > 0) {
                    keranjang.add(m);
                }
            }

            if (keranjang.isEmpty()) {
                Toast.makeText(this, "Silakan pilih menu terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
//                // Berpindah ke CheckoutActivity (bawa list objek Menu)
//                Intent intent = new Intent(DetailTokoActivity.this, CheckoutActivity.class);
//                intent.putExtra("LIST_PESANAN", keranjang);
//                startActivity(intent);
            }
        });
    }

    private void populateMenuData() {
        if (idToko == 1) { // Warung Mang Ade
            // Langsung masukkan idToko (int) ke parameter kedua
            listDataMenu.add(new Menu(101, idToko, namaToko, "Nasi Goreng Ade", 15000, R.drawable.ic_user_avatar));
            listDataMenu.add(new Menu(102, idToko, namaToko, "Es Teh Manis", 5000, R.drawable.ic_user_avatar));
        } else if (idToko == 2) {
            listDataMenu.add(new Menu(201, idToko, namaToko, "Mie Ayam Bakso", 12000, R.drawable.ic_user_avatar));
        }
    }

    @Override
    public void onTotalChanged(int newTotal) {
        tvTotalPrice.setText("Rp " + String.format("%,d", newTotal).replace(',', '.'));
    }
}