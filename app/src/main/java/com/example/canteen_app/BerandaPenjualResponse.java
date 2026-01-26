package com.example.canteen_app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BerandaPenjualResponse {
    private TokoModel toko;
    @SerializedName("total_pemasukan")
    private double totalPemasukan;
    @SerializedName("total_selesai")
    private int totalSelesai;
    @SerializedName("pesanan_aktif")
    private List<OrderModel> pesananAktif;

    // Getters
    public TokoModel getToko() { return toko; }
    public double getTotalPemasukan() { return totalPemasukan; }
    public int getTotalSelesai() { return totalSelesai; }
    public List<OrderModel> getPesananAktif() { return pesananAktif; }

    public class TokoModel {
        @SerializedName("shop_name")
        private String shopName;
        public String getShopName() { return shopName; }
    }

    public class OrderModel {
        @SerializedName("order_id")
        private String orderId;
        @SerializedName("nama_pembeli")
        private String namaPembeli;
        @SerializedName("rincian_menu")
        private String rincianMenu;
        @SerializedName("total_harga")
        private int totalHarga;
        @SerializedName("note")
        private String note;
        @SerializedName("pickup_time")
        private String pickupTime;

        public String getOrderId() { return orderId; }
        public String getNamaPembeli() { return namaPembeli; }
        public int getTotalHarga() { return totalHarga; }
        public String getPickupTime() { return pickupTime; }
        public String getNote() {
            return (note != null && !note.isEmpty()) ? note : "-";
        }

        public String getRincianMenu() {
            return rincianMenu != null ? rincianMenu : "Tidak ada detail";
        }
    }
}
