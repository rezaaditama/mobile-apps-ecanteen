package com.example.canteen_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.ViewHolder> {
    private List<BerandaPenjualResponse.OrderModel> orderList;

    public LaporanAdapter(List<BerandaPenjualResponse.OrderModel> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laporan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BerandaPenjualResponse.OrderModel order = orderList.get(position);

        holder.tvNama.setText(order.getNamaPembeli());
        holder.tvHarga.setText("Rp " + String.format("%,d", order.getTotalHarga()));
        holder.tvOrderId.setText("#" + order.getOrderId());

//        Menampilkan waktu pengambilan
        if (order.getPickupTime() != null && !order.getPickupTime().isEmpty()) {
            holder.tvTanggal.setText("Ambil: " + order.getPickupTime());
        } else {
            holder.tvTanggal.setText("Waktu tidak set");
        }

        holder.btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailPemesananActivity.class);
            intent.putExtra("ORDER_ID", order.getOrderId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderList != null ? orderList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHarga, tvOrderId, tvTanggal, btnDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama_pembeli);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvHarga = itemView.findViewById(R.id.tv_total_harga);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            btnDetail = itemView.findViewById(R.id.btn_detail_laporan);
        }
    }
}