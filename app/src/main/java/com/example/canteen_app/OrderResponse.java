package com.example.canteen_app;

import com.google.gson.annotations.SerializedName;

public class OrderResponse {
//    Deklarasi Variabel
    @SerializedName("message")
    private String message;
    @SerializedName("snap_token")
    private String snapToken;
    @SerializedName("redirect_url")
    private String redirectUrl;

//    Getter
    public String getRedirectUrl() { return redirectUrl; }
    public String getSnapToken() { return snapToken; }
}
