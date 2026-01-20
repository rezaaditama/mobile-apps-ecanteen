package com.example.canteen_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

//    Deklarasi variabel
    private Context context;
    private List<Menu> listData;
    private CardListener listener;

    public CartAdapter(Context context, List<Menu> listData, CardListener listener) {
        this.context = context;
        this.listData = listData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_keranjang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//       Ambil data
        Menu item = listData.get(position);

//        Ganti tampilan
        holder.tvNama.setText(item.getProductName());
        holder.tvHarga.setText("Rp " + String.format("%,d", item.getProductPrice()));
        holder.tvQty.setText(String.valueOf(item.getQty()));
        holder.imgProduk.setImageResource(item.getProductPath());

        // Sembunyikan jika kosong
        if (item.getNote() != null && !item.getNote().isEmpty()) {
            holder.tvNote.setVisibility(View.VISIBLE);
            holder.tvNote.setText("Catatan: " + item.getNote());
        } else {
//            Kalau catatan kosong
            holder.tvNote.setVisibility(View.GONE);
        }

        // Tombol Plus
        holder.btnPlus.setOnClickListener(v -> {
            item.setQty(item.getQty() + 1);
            CartManager.getInstance().addOrUpdateItem(item); // Update di Singleton
            notifyItemChanged(position);
            listener.onTotalChanged(0);
        });

        // Tombol Minus (Logika hapus otomatis jika 0)
        holder.btnMinus.setOnClickListener(v -> {
            int newQty = item.getQty() - 1;
            item.setQty(newQty);

            CartManager.getInstance().addOrUpdateItem(item); // Ini otomatis hapus di Singleton jika qty <= 0

            if (newQty <= 0) {
                listData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listData.size());
            } else {
                notifyItemChanged(position);
            }
            listener.onTotalChanged(0);
        });
}

//Ambil jumlah item
    @Override
    public int getItemCount() {
        return listData.size();
    }

//    Tampilan recycler vier
public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvNama, tvHarga, tvQty, tvNote;
    ImageView imgProduk;
    ImageButton btnPlus, btnMinus;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.tv_nama_produk_keranjang);
        tvHarga = itemView.findViewById(R.id.tv_harga_produk_keranjang);
        tvQty = itemView.findViewById(R.id.tv_qty_keranjang);
        tvNote = itemView.findViewById(R.id.tv_catatan_produk);
        imgProduk = itemView.findViewById(R.id.img_produk_keranjang);
        btnPlus = itemView.findViewById(R.id.btn_plus_keranjang);
        btnMinus = itemView.findViewById(R.id.btn_minus_keranjang);
    }
}
}