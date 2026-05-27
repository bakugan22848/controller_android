package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class TriggerUpdate {
    @SerializedName("name")
    private String name;

    @SerializedName("notif_state")
    private Boolean notifState;

    @SerializedName("notif_value")
    private Float notifValue;

    @SerializedName("check_clock")
    private Integer checkClock;

    @SerializedName("write_clock")
    private Integer writeClock;

    public TriggerUpdate(String name, Boolean notifState, Integer checkClock, Float notifValue, Integer writeClock) {
        this.name = name;
        this.notifState = notifState;
        this.checkClock = checkClock;
        this.notifValue = notifValue;
        this.writeClock = writeClock;
    }

    public String getName() {
        return name;
    }



    public Boolean getNotifState() {
        return notifState;
    }

    public Float getNotifValue() {
        return notifValue;
    }

    public Integer getCheckClock() {
        return checkClock;
    }

    public Integer getWriteClock() {
        return writeClock;
    }

    // Constructor and getters...
}
