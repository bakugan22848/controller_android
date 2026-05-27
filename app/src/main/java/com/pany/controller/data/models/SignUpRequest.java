package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public SignUpRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
