package com.example.canteen_app;

public class Menu {
    private String nama;
    private int harga;
    private int gambar;

    public Menu(String nama, int harga, int gambar) {
        this.nama = nama;
        this.harga = harga;
        this.gambar = gambar;
    }

    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public int getGambar() { return gambar; }
}
