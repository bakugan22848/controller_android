package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class ControllerCreate {
    @SerializedName("name")
    private String name;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("trigger_value")
    private Integer triggerValue;

    @SerializedName("pin")
    private Integer pin;


    public ControllerCreate(String name, String deviceId, Integer triggerValue, Integer pin) {
        this.name = name;
        this.deviceId = deviceId;
        this.triggerValue = triggerValue;
        this.pin = pin;
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

    public Integer getPin() {
        return pin;
    }
}
