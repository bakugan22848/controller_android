package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class DeviceCreate {
    @SerializedName("name")
    private String name;

    public DeviceCreate(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}
