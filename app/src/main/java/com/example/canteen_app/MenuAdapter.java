package com.example.canteen_app;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

//    Inisialisasi
    private Context context;
    private List<Menu> menuList;
    private CardListener listener; // Interface yang kamu buat tadi
    private int totalHarga = 0;

    public MenuAdapter(Context context, List<Menu> menuList, CardListener listener) {
        this.context = context;
        this.menuList = menuList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.tvNama.setText(menu.getNama());
        holder.tvHarga.setText("Rp " + String.format("%,d", menu.getHarga()).replace(',', '.'));
        holder.imgMenu.setImageResource(menu.getGambar());

        final int[] qty = {0};

        // Tombol TAMBAH (+)
        holder.btnPlus.setOnClickListener(v -> {
            if (qty[0] == 0) {
                showNoteDialog(menu.getNama());
                holder.btnMinus.setVisibility(View.VISIBLE);
                holder.tvQty.setVisibility(View.VISIBLE);
            }
            qty[0]++;
            holder.tvQty.setText(String.valueOf(qty[0]));

            // Update Total
            totalHarga += menu.getHarga();
            listener.onTotalChanged(totalHarga);
        });

        // Tombol KURANG (-)
        holder.btnMinus.setOnClickListener(v -> {
            if (qty[0] > 0) {
                qty[0]--;
                holder.tvQty.setText(String.valueOf(qty[0]));

                totalHarga -= menu.getHarga();
                listener.onTotalChanged(totalHarga);

                if (qty[0] == 0) {
                    holder.btnMinus.setVisibility(View.GONE);
                    holder.tvQty.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showNoteDialog(String namaMenu) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Catatan untuk " + namaMenu);
        final EditText input = new EditText(context);
        input.setHint("Contoh: Tidak pakai sambal...");
        builder.setView(input);
        builder.setPositiveButton("Simpan", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    public int getItemCount() { return menuList.size(); }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHarga, tvQty;
        ImageView imgMenu;
        ImageButton btnPlus, btnMinus;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama_menu);
            tvHarga = itemView.findViewById(R.id.tv_harga_menu);
            tvQty = itemView.findViewById(R.id.tv_qty);
            imgMenu = itemView.findViewById(R.id.img_menu);
            btnPlus = itemView.findViewById(R.id.btn_plus);
            btnMinus = itemView.findViewById(R.id.btn_minus);
        }
    }
}