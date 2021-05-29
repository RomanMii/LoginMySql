package com.ti38b.loginmysql.models;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int resultCode;

    @SerializedName("username")
    private String username;

    public String getStatus() {
        return status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getUsername() {
        return username;
    }
}
