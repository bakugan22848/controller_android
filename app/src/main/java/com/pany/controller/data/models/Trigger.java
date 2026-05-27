package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Trigger {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("device_id")
    private String deviceId;


    @SerializedName("notif_state")
    private Boolean notifState;

    @SerializedName("last_value")
    private Float lastValue;

    @SerializedName("notif_value")
    private Float notifValue;

    @SerializedName("check_clock")
    private Integer checkClock;

    @SerializedName("write_clock")
    private Integer writeClock;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }


    public Boolean getNotifState() { return notifState; }
    public void setNotifState(Boolean notifState) { this.notifState = notifState; }

    public Float getLastValue() { return lastValue; }
    public void setLastValue(Float lastValue) { this.lastValue = lastValue; }

    public Float getNotifValue() { return notifValue; }
    public void setNotifValue(Float notifValue) { this.notifValue = notifValue; }

    public Integer getCheckClock() { return checkClock; }
    public void setCheckClock(Integer checkClock) { this.checkClock = checkClock; }

    public Integer getWriteClock() { return writeClock; }
    public void setWriteClock(Integer writeClock) { this.writeClock = writeClock; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}