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

    @SerializedName("check_clock")
    private Integer checkClock;

    @SerializedName("write_clock")
    private Integer writeClock;

    public TriggerCreate(String name, String deviceId, Boolean notifState, Float notifValue,
                                Integer checkClock, Integer writeClock) {
        this.name = name;
        this.deviceId = deviceId;
        this.notifState = notifState;
        this.notifValue = notifValue;
        this.checkClock = checkClock;
        this.writeClock = writeClock;
    }

    public Integer getWriteClock() {
        return writeClock;
    }

    public Integer getCheckClock() {
        return checkClock;
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

    // Getters...
}
