package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class TriggerCreate {
    @SerializedName("name")
    private String name;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("notif_state")
    private Boolean notifState;

    @SerializedName("notif_value")
    private Float notifValue;

    @SerializedName("type")
    private String type;

    @SerializedName("pin")
    private Integer pin;

    public TriggerCreate(String name, String deviceId, Boolean notifState, Float notifValue,
                                String type, Integer pin) {
        this.name = name;
        this.deviceId = deviceId;
        this.notifState = notifState;
        this.notifValue = notifValue;
        this.type = type;
        this.pin = pin;
    }
    public Float getNotifValue() {
        return notifValue;
    }

    public Boolean getNotifState() {
        return notifState;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getPin() {
        return pin;
    }
}
