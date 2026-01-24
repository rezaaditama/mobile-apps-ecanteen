package com.example.canteen_app;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    // Getter
    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
