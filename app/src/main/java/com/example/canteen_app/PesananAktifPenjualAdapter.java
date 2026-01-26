package com.example.canteen_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PesananAktifPenjualAdapter extends RecyclerView.Adapter<PesananAktifPenjualAdapter.ViewHolder> {
//    Inisialisasi Komponen
    private List<BerandaPenjualResponse.OrderModel> list;
    private Context context;

    public PesananAktifPenjualAdapter(List<BerandaPenjualResponse.OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pesanan_penjual, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BerandaPenjualResponse.OrderModel order = list.get(position);

        // Set data ke TextView
        holder.tvHeader.setText("#" + order.getOrderId() + " • " + order.getNamaPembeli());
        holder.tvMenu.setText(order.getRincianMenu());

        // Klik tombol Proses
        holder.btnProses.setOnClickListener(v -> {
            Intent intent = new Intent(context, proses_pesanan.class);
            intent.putExtra("ORDER_ID", order.getOrderId());
            context.startActivity(intent);
        });
    }

//    Jumlah item
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader, tvMenu;
        Button btnProses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tv_item_header);
            tvMenu = itemView.findViewById(R.id.tv_item_menu);
            btnProses = itemView.findViewById(R.id.btn_item_proses);
        }
    }
}