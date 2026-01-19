package com.example.canteen_app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BerandaActivity extends AppCompatActivity {

//    private Button btnSemua, btnMakanan, btnMinuman;
    private RecyclerView rvToko;
    private TokoAdapter adapter;
    private List<Toko> listToko;
    private LinearLayout navOrders, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beranda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Inisialisasi Komponen
//    btnSemua = findViewById(R.id.btn_semua);
//    btnMakanan = findViewById(R.id.btn_makanan);
//    btnMinuman = findViewById(R.id.btn_minuman);
    rvToko = findViewById(R.id.rv_daftar_toko);
    navOrders = findViewById(R.id.menu_history);
    navProfile = findViewById(R.id.menu_profile);

//    Click Listener
//        btnSemua.setOnClickListener(v -> setTombolAktif(btnSemua, btnMakanan, btnMinuman));
//        btnMakanan.setOnClickListener(v -> setTombolAktif(btnMakanan, btnSemua, btnMinuman));
//        btnMinuman.setOnClickListener(v -> setTombolAktif(btnMinuman, btnSemua, btnMakanan));

//    Fetching Data
    prepareData();

//    Inisialisasi RecyclerView Menggunakan GridLayoutManager
    rvToko.setLayoutManager(new GridLayoutManager(this, 2));

//    Menambahkan Padding
        rvToko.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(android.graphics.Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int padding = 10; // Sesuaikan jaraknya
                outRect.left = padding;
                outRect.right = padding;
                outRect.top = padding;
                outRect.bottom = padding;
            }
        });


//    Hubungkan data ke adapter
    adapter = new TokoAdapter(this, listToko);
    rvToko.setAdapter(adapter);
}

// Quick LInk
    private void setupNavigation(){
        navOrders.setOnClickListener(v -> {
            // Un-comment baris di bawah jika OrdersActivity sudah dibuat
            // Intent intent = new Intent(BerandaActivity.this, OrdersActivity.class);
            // startActivity(intent);
            // overridePendingTransition(0, 0); // Menghilangkan animasi agar transisi smooth
        });

        navProfile.setOnClickListener(v -> {
            // Un-comment baris di bawah jika ProfileActivity sudah dibuat
            // Intent intent = new Intent(BerandaActivity.this, ProfileActivity.class);
            // startActivity(intent);
            // overridePendingTransition(0, 0);
        });
    }

// Fungsi list toko
private void prepareData() {
    listToko = new ArrayList<>();
    listToko.add(new Toko(1, "Warung Mang Ade", "Kantin UIKA Lantai 1", R.drawable.ic_user_avatar));
    listToko.add(new Toko(2, "Mama's Noodle Bar", "Kantin UIKA Lantai 2", R.drawable.ic_user_avatar));
    listToko.add(new Toko(3, "Warung Mang Anto", "Kantin UIKA Lantai 1", R.drawable.ic_user_avatar));
    listToko.add(new Toko(4, "Warung Baso", "Kantin UIKA Lantai 1", R.drawable.ic_user_avatar));
}

//    Set Tombol Aktif
    private void setTombolAktif(Button tombolAktif, Button tombolTidakAktif1, Button tombolTidakAktif2) {
// Efek untuk tombol yang DIKLIK (Active)
        tombolAktif.setBackgroundResource(R.drawable.btn_toogle_active);
        tombolAktif.setTextColor(Color.WHITE);

//        Ambil warna dari res
        int warnaPrimary = ContextCompat.getColor(this, R.color.primary);

        // Efek untuk tombol lainnya (Inactive)
        tombolTidakAktif1.setBackgroundResource(R.drawable.btn_toogle_inactive);
        tombolTidakAktif1.setTextColor(warnaPrimary);

        tombolTidakAktif2.setBackgroundResource(R.drawable.btn_toogle_inactive);
        tombolTidakAktif2.setTextColor(warnaPrimary);
    }
}