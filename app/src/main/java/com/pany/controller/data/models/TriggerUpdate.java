package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class TriggerUpdate {
    @SerializedName("name")
    private String name;

    @SerializedName("notif_state")
    private Boolean notifState;

    @SerializedName("notif_value")
    private Float notifValue;

    @SerializedName("type")
    private String type;

    @SerializedName("pin")
    private Integer pin;

    public TriggerUpdate(String name, Boolean notifState, Float notifValue, String type, Integer pin) {
        this.name = name;
        this.notifState = notifState;
        this.notifValue = notifValue;
        this.type = type;
        this.pin = pin;
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

    public String getType() {
        return type;
    }

    public Integer getPin() {
        return pin;
    }

}
