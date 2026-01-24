package com.example.canteen_app;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Menu implements Serializable {
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("product_price")
    private int product_price;
    @SerializedName("product_path")
    private String product_path_string;
    @SerializedName("product_id")
    private int product_id;
    @SerializedName("shop_id")
    private int shop_id;
    @SerializedName("note")
    private String note = "";

    //    Join dengan tabel orders_items
    private int qty = 0;
    private String shop_name;

    //Helper
//    Menampung resource ID path
    private int resIdImage = 0;

    // VARIABEL UNTUK ORDER (Tambahkan ini agar Order.java tidak error)
    private String parentOrderId;
    private String parentPickupTime;
    private String paymentMethod;
    private boolean isFinished = false;


    public Menu() {
    }

    //    KOnversi string gambar dari DB menjadi resource ID
    public void convertPathToResourceId(android.content.Context context) {
        if (product_path_string != null) {
            this.resIdImage = context.getResources().getIdentifier(
                    product_path_string, "drawable", context.getPackageName()
            );
        }
    }

    //    Getter
    public String getProductName() {
        return product_name;
    }

    public int getProductPrice() {
        return product_price;
    }

    public int getProductPath() { return resIdImage; }

    public int getQty() {
        return qty;
    }

    public String getNote() {
        return note;
    }

    public int getProductId() {
        return product_id;
    }

    public int getShopId() {
        return shop_id;
    }

    public String getShopName() {
        return shop_name;
    }

    //    Setter
    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setShopName(String shop_name) {
        this.shop_name = shop_name;
    }

    // METHOD TITIPAN
    public void setParentOrderId(String orderId) {
        this.parentOrderId = orderId;
    }

    public void setParentPickupTime(String pickupTime) {
        this.parentPickupTime = pickupTime;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public String getParentOrderId() {
        return parentOrderId;
    }
    public String getParentPickupTime() {
        return parentPickupTime;
    }
    public boolean isFinished() {
        return isFinished;
    }

}