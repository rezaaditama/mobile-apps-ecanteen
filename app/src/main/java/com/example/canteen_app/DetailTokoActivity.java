package com.example.canteen_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTokoActivity extends AppCompatActivity implements CardListener {

//    Deklarasi Komponen
    private RecyclerView rvMenu;
    private TextView tvTotalProduct, tvNamaTokoHeader;
    private MenuAdapter adapter;
    private List<Menu> listDataMenu;
    private Button btnCart;

    // Variabel untuk menampung data dari Intent
    private int shopId;
    private String shopName;
    private int shopImageResId;

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
        tvTotalProduct = findViewById(R.id.tv_total_product);
        tvNamaTokoHeader = findViewById(R.id.tv_nama_toko);
        btnCart = findViewById(R.id.btn_cart);

//        Tombol back
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        // Tangkap Data dari Intent (TokoAdapter)
        shopId = getIntent().getIntExtra("ID_TOKO", 0);
        shopName = getIntent().getStringExtra("NAMA_TOKO");
        String shopImageName = getIntent().getStringExtra("GAMBAR_TOKO");

//        Konversi nama foto jadi resource id
        if (shopImageName != null) {
            shopImageResId = getResources().getIdentifier(shopImageName, "drawable", getPackageName());
        }

//        Jika gambar tidak ditemukan
        if (shopImageResId == 0) {
            shopImageResId = R.drawable.ic_launcher_background;
        }

//        Manipulasi nama toko
        if (tvNamaTokoHeader != null) tvNamaTokoHeader.setText(shopName != null ? shopName : "Detail Toko");

//        Manipulasi Foto toko
        ImageView imgHeader = findViewById(R.id.img_detail_toko);
        if (imgHeader != null) imgHeader.setImageResource(shopImageResId);

//        Inisialisasi List dan Adapter
        listDataMenu = new ArrayList<>();
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MenuAdapter(this, listDataMenu, this);
        rvMenu.setAdapter(adapter);

//        Update diawal reload
        populateMenuData();
        updateBottomBar();

        // Logika Tombol Cart
        btnCart.setOnClickListener(v -> {
            if (CartManager.getInstance().getCartList().isEmpty()) {
                Toast.makeText(this, "Keranjang masih kosong!", Toast.LENGTH_SHORT).show();
            } else {
                // Pindah ke keranjang activity
                 Intent intent = new Intent(this, KeranjangActivity.class);
                 startActivity(intent);
            }
        });
    }

//    Refresh data ketika onResume
    @Override
    protected void onResume() {
        super.onResume();
        populateMenuData();
        updateBottomBar();
    }

    private void populateMenuData() {
        if (shopId == 0) return;

//        Panggil APiService
        ApiService apiService = RetrofitClient.getApiService();

        // Ambil menu berdasarkan ID Toko dari server
        apiService.getMenuByToko(shopId).enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {

    //        Kalau fetching sukses
                if (response.isSuccessful() && response.body() != null) {
                    List<Menu> fetchedMenu = response.body();
                    List<Menu> currentCart = CartManager.getInstance().getCartList();

                    listDataMenu.clear();

                    for (Menu mRaw : fetchedMenu) {
    //                    Ubah string gambar menjadi id resource
                        mRaw.convertPathToResourceId(DetailTokoActivity.this);

    //                    Set Shop Name untuk cart
                        mRaw.setShopName(shopName);

    //                    Sinkron dengan CartManager
                        Menu foundInCart = null;
                        for (Menu mCart : currentCart) {
                            if (mRaw.getProductId() == mCart.getProductId()) {
                                foundInCart = mCart;
                                break;
                            }
                        }

    //                  Kalau ada di cart, ambil dari cart
                        if (foundInCart != null) {
                            listDataMenu.add(foundInCart);
                        } else {
                            listDataMenu.add(mRaw);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DetailTokoActivity.this, "Gagal mengambil menu", Toast.LENGTH_SHORT).show();
                }
            }

//        Kalau gagal mengambil menu
        @Override
        public void onFailure(Call<List<Menu>> call, Throwable t) {
            Log.e("API_ERROR", "Error: " + t.getMessage());
            Toast.makeText(DetailTokoActivity.this, "Cek koneksi internet", Toast.LENGTH_SHORT).show();
        }
    });
}

    // Fungsi pembantu untuk update tampilan jumlah produk di bar bawah
    private void updateBottomBar() {
        int totalQty = 0;
        for (Menu item : CartManager.getInstance().getCartList()) {
            totalQty += item.getQty();
        }

        // Tampilkan: "X Produk"
        tvTotalProduct.setText(totalQty + " Produk");

        // Opsional: Sembunyikan bar jika keranjang kosong
        View checkoutBar = findViewById(R.id.layout_checkout_bar);
        if (totalQty == 0) {
            findViewById(R.id.layout_checkout_bar).setVisibility(View.GONE);
        } else {
            findViewById(R.id.layout_checkout_bar).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTotalChanged(int newTotal) {
        // Ketika ada perubahan +/- di adapter, panggil update bar bawah
        updateBottomBar();
    }
}

