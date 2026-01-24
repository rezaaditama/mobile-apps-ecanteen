package com.example.canteen_app;

import com.google.gson.annotations.SerializedName;

    public class LoginResponse {
        private String message;

        @SerializedName("user_id")
        private int userId;
        @SerializedName("nomor_telepon")
        private String nomorTelepon;

        private String nama;
        private String email;

        // Getter
        public String getMessage() { return message; }
        public int getUserId() { return userId; }
        public String getNama() { return nama; }
        public String getEmail() { return email; }
        public String getNomorTelepon() { return nomorTelepon; }
    }
