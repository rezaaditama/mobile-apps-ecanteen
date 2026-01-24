package com.example.canteen_app;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("nama_lengkap")
    private String namaLengkap;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("nomor_telepon")
    private String nomorTelepon;

    @SerializedName("role")
    private String role; // 'pembeli' atau 'penjual'

    public User(String namaLengkap, String email, String password, String nomorTelepon, String role) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
        this.nomorTelepon = nomorTelepon;
        this.role = role;
    }
}
