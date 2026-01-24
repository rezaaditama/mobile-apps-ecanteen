package com.example.canteen_app;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{
//    Inisialisasi Variabel
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("pickup_time")
    private String pickupTime;
    @SerializedName("total_harga")
    private int totalHarga;
    @SerializedName("is_finished")
    private boolean isFinished;
    @SerializedName("payment_method")
    private String paymentMethod;

//    List yang dikirim ke table order_items
    @SerializedName("items")
    private List<Menu> items;

    public Order(String orderId, List<Menu> items, String pickupTime, int totalHarga, String paymentMethod, int userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.pickupTime = pickupTime;
        this.totalHarga = totalHarga;
        this.paymentMethod = paymentMethod;

        for (Menu m : items) {
            m.setParentOrderId(orderId);
            m.setParentPickupTime(pickupTime);
            m.setPaymentMethod(paymentMethod);
        }
    }

    // Gette
    public String getOrderId() { return orderId; }
    public List<Menu> getItems() { return items; }
    public String getPickupTime() { return pickupTime; }
    public int getTotalHarga() { return totalHarga; }
    public boolean isFinished() { return isFinished; }
    public String getPaymentMethod() { return paymentMethod; }

//    Setter
    public void setFinished(boolean finished) { isFinished = finished; }
}
