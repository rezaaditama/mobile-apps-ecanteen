package com.example.canteen_app;

public class Toko {
    private int shop_id;
    private String shop_name;
    private String shop_location;
    private String shop_src;

    public Toko(int shop_id, String shop_name, String shop_location, String shop_src) {
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_location = shop_location;
        this.shop_src = shop_src;
    }

//    Getter
    public int getShop_id() {return shop_id;}
    public String getShop_name() {return shop_name;}
    public String getShop_location() {return shop_location;}
    public String getShop_src() {return shop_src;}

}
