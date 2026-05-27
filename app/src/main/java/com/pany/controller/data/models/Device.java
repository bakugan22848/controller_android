package com.pany.controller.data.models;


import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Device {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("periph_count")
    private int periphCount;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }



    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public int getPeriphCount() {
        return periphCount;
    }

    public void setPeriphCount(int periphCount) {
        this.periphCount = periphCount;
    }
}