package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class DeviceUpdate {
    @SerializedName("name")
    private String name;

    public DeviceUpdate(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}
