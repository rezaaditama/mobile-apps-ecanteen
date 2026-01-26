package com.example.canteen_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.PesananViewHolder> {
//    Inisialisasi Variabel
    private final Context context;
    private final List<Menu> listMenu;

    public PesananAdapter(Context context, List<Menu> listMenu) {
        this.context = context;
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public PesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pesanan, parent, false);
        return new PesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananViewHolder holder, int position) {
        Menu menu = listMenu.get(position);

        // Set gambar
        if (menu.getProductPath() != 0) {
            holder.imgMenu.setImageResource(menu.getProductPath());
        } else {
            holder.imgMenu.setImageResource(R.drawable.logo);
        }
//        set nama toko dan nama menu
        holder.tvNamaToko.setText(menu.getShopName() != null ? menu.getShopName() : "Toko Kantin");
        holder.tvNamaMenu.setText(String.format("%s (x%d)", menu.getProductName(), menu.getQty()));
        holder.tvOrderId.setText(String.format("ID: #%s", menu.getParentOrderId()));
        holder.tvWaktuAmbil.setText(String.format("Ambil: %s", menu.getParentPickupTime()));

//        Format harga
        int totalHargaItem = menu.getProductPrice() * menu.getQty();
        holder.tvHarga.setText(String.format("Rp %,d", totalHargaItem).replace(',', '.'));

        // Tampilkan catatan
        if (menu.getNote() != null && !menu.getNote().isEmpty()) {
            holder.tvCatatan.setText("Catatan: " + menu.getNote());
            holder.tvCatatan.setVisibility(View.VISIBLE);
        } else {
            holder.tvCatatan.setVisibility(View.GONE);
        }

//        Update UI
        updateStatusUI(holder, menu);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StatusQrisActivity.class);
            intent.putExtra("ITEM_PESANAN", menu);
            context.startActivity(intent);
        });
    }

    private void updateStatusUI(PesananViewHolder holder, Menu menu) {

        // Set data dari Order Induk
        String statusMidtrans = menu.getStatusPembayaran();
        String paymentMethod = menu.getPaymentMethod();

        if (menu.isFinished()) {
            holder.tvStatus.setText("Pesanan Selesai");
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
            return;
        }

        if ("cancel".equalsIgnoreCase(statusMidtrans) ||
                "expire".equalsIgnoreCase(statusMidtrans) ||
                "deny".equalsIgnoreCase(statusMidtrans)) {
            holder.tvStatus.setText("Transaksi Gagal");
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
            return;
        }

        if ("Tunai".equalsIgnoreCase(paymentMethod)) {
            // Jika Tunai, biasanya status di DB langsung 'settlement'
            holder.tvStatus.setText("Bayar di Kasir");
            holder.tvStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));
        } else {
            // Untuk Non-Tunai (QRIS/Transfer)
            if ("settlement".equalsIgnoreCase(statusMidtrans)) {
                holder.tvStatus.setText("Lunas - Siap Diambil");
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
            } else {
                holder.tvStatus.setText("Menunggu Pembayaran");
                holder.tvStatus.setTextColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark));
            }
        }
    }

//    Mengambil total item
    @Override
    public int getItemCount() {
        return (listMenu != null) ? listMenu.size() : 0;
    }

//    Data recycle
public static class PesananViewHolder extends RecyclerView.ViewHolder {
    ImageView imgMenu;
    TextView tvNamaToko, tvNamaMenu, tvHarga, tvCatatan, tvOrderId, tvWaktuAmbil, tvStatus;

    public PesananViewHolder(@NonNull View itemView) {
        super(itemView);
        imgMenu = itemView.findViewById(R.id.imgMenuPesanan);
        tvNamaToko = itemView.findViewById(R.id.tvNamaTokoPesanan);
        tvNamaMenu = itemView.findViewById(R.id.tvNamaMenu);
        tvHarga = itemView.findViewById(R.id.tvHargaPesanan);
        tvCatatan = itemView.findViewById(R.id.tvCatatanPesanan);
        tvOrderId = itemView.findViewById(R.id.tvIdPesanan);
        tvWaktuAmbil = itemView.findViewById(R.id.tvWaktuAmbil);
        tvStatus = itemView.findViewById(R.id.tvStatusPesanan);
    }
}
}
