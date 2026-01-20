package com.example.canteen_app;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{
//    Inisialisasi Variabel
    private String orderId;
    private List<Menu> items;
    private String pickupTime;
    private int totalHarga;
    private boolean isFinished;

    public Order(String orderId, List<Menu> items, String pickupTime, int totalHarga) {
        this.orderId = orderId;
        this.items = items;
        this.pickupTime = pickupTime;
        this.totalHarga = totalHarga;
        this.isFinished = false;
    }

    // Gette
    public String getOrderId() { return orderId; }
    public List<Menu> getItems() { return items; }
    public String getPickupTime() { return pickupTime; }
    public int getTotalHarga() { return totalHarga; }
    public boolean isFinished() { return isFinished; }

//    Setter
    public void setFinished(boolean finished) { isFinished = finished; }
}
