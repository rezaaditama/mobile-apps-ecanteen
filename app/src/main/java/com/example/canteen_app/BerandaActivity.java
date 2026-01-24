package com.example.canteen_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BerandaActivity extends AppCompatActivity {
//    Deklarasi Variabel
    private RecyclerView rvToko;
    private TokoAdapter adapter;
    private List<Toko> listToko;
    private LinearLayout navOrders, navProfile;
    private ImageView btnCart;
    private TextView tvGreetingName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beranda);

        // Cek user
        SharedPreferences sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false);

        if (!isLoggedIn) {
            // Jika belum login, lempar kembali ke LoginActivity
            Intent intent = new Intent(BerandaActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Ambil komponen berdasarkan ID
    rvToko = findViewById(R.id.rv_daftar_toko);
    navOrders = findViewById(R.id.menu_history);
    navProfile = findViewById(R.id.menu_profile);
    btnCart = findViewById(R.id.btn_cart);
    tvGreetingName = findViewById(R.id.tv_greeting_2);

//    Manipulasi nama
        String namaLengkap = sharedPreferences.getString("USER_NAMA", "User");
        String namaPanggilan = namaLengkap;

//        Nama kata pertama
        if (namaLengkap != null && !namaLengkap.trim().isEmpty()) {
            // Memecah String berdasarkan spasi
            String[] kata = namaLengkap.trim().split("\\s+");
            if (kata.length > 0) {
                namaPanggilan = kata[0]; // Ambil kata pertama saja
            }
        }

//    Set nama
        tvGreetingName.setText(namaPanggilan + "! 🎓");

//    Inisialisasi List dan Adapter
        listToko = new ArrayList<>();
        adapter = new TokoAdapter(this, listToko);
        rvToko.setAdapter(adapter);


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


//        Panggil navigasi
        setupNavigation();

//    Fetching Data dari endpoint https://be-mobile-ecanteen.vercel.app/api/toko
        prepareData();

        //    Cart Button
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(BerandaActivity.this, KeranjangActivity.class);
            startActivity(intent);
        });
    }

// Quick Link
    private void setupNavigation(){
        navOrders.setOnClickListener(v -> {
            // pindah ke halaman pesanan
             Intent intent = new Intent(BerandaActivity.this, PesananAktifActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);

        });

        navProfile.setOnClickListener(v -> {
// pindah ke halaman profile
             Intent intent = new Intent(BerandaActivity.this, ProfilActivity.class);
             startActivity(intent);
             overridePendingTransition(0, 0);
        });
    }

// Fungsi list toko
private void prepareData() {
//    Panggil API Service
    ApiService apiService = RetrofitClient.getApiService();

//    Get request ke https://be-mobile-ecanteen.vercel.app/api/toko
    apiService.getDaftarToko().enqueue(new Callback<List<Toko>>() {
        @Override
        public void onResponse(Call<List<Toko>> call, Response<List<Toko>> response) {
            if (response.isSuccessful() && response.body() != null) {
                // Bersihkan list lama dan masukkan data baru dari server
                listToko.clear();
                listToko.addAll(response.body());

                // Beritahu adapter untuk memperbarui tampilan UI
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(BerandaActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<List<Toko>> call, Throwable t) {
            // Log jika terjadi masalah koneksi atau parsing
            Log.e("API_ERROR", "Error: " + t.getMessage());
            Toast.makeText(BerandaActivity.this, "Cek koneksi internet Anda", Toast.LENGTH_SHORT).show();
        }
    });
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