package com.example.canteen_app;

import java.io.Serializable;

public class Menu implements Serializable{
    private String product_name;
    private int product_price;
    private int product_path;
    private int qty = 0;
    private String note = "";
    private int product_id;
    private int shop_id;
    private String shop_name;
    private boolean isFinished = false;
    private String parentOrderId;
    private String parentPickupTime;




    public Menu(int product_id, int shop_id, String shop_name, String product_name, int product_price, int product_path) {    this.product_id = product_id;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_path = product_path;
    }

//    Getter
    public String getProductName() { return product_name; }
    public int getProductPrice() { return product_price; }
    public int getProductPath() { return product_path; }
    public boolean isFinished() { return isFinished; }
    public int getQty() { return qty; }
    public String getNote() { return note; }
    public int getProductId() { return product_id; }
    public int getShopId() { return shop_id; }
    public String getShopName() { return shop_name; }
    public String getParentOrderId() { return parentOrderId; }
    public String getParentPickupTime() { return parentPickupTime; }

//    Setter
    public void setQty(int qty) { this.qty = qty; }
    public void setNote(String note) { this.note = note; }
    public void setFinished(boolean finished) { isFinished = finished; }
    public void setParentOrderId(String id) { this.parentOrderId = id; }
    public void setParentPickupTime(String time) { this.parentPickupTime = time; }


}
