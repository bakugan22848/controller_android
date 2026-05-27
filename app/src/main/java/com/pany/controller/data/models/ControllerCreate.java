package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class ControllerCreate {
    @SerializedName("name")
    private String name;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("trigger_value")
    private Integer triggerValue;

    public ControllerCreate(String name, String deviceId, Integer triggerValue) {
        this.name = name;
        this.deviceId = deviceId;
        this.triggerValue = triggerValue;
    }

    public String getName() {
        return name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Integer getTriggerValue() {
        return triggerValue;
    }

}
