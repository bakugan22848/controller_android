package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;


public class Controller {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("trigger_id")
    private String triggerId;

    @SerializedName("trigger_vector")
    private String triggerVector;

    @SerializedName("last_state")
    private Boolean lastState;

    @SerializedName("trigger_value")
    private Integer triggerValue;

    @SerializedName("is_automatic")
    private Boolean isAutomatic;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getLastState() {
        return lastState;
    }

    public void setLastState(Boolean lastState) {
        this.lastState = lastState;
    }

    public Integer getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(Integer triggerValue) {
        this.triggerValue = triggerValue;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public String getTriggerVector() {
        return triggerVector;
    }

    public void setTriggerVector(String trigger_vector) {
        this.triggerVector = trigger_vector;
    }

    public Boolean getAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(Boolean automatic) {
        isAutomatic = automatic;
    }
}