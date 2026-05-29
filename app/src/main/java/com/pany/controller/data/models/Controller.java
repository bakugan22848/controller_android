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
    @SerializedName("pin")
    private Integer pin;

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

    public Boolean getLastState() {
        return lastState;
    }


    public Integer getTriggerValue() {
        return triggerValue;
    }


    public String getTriggerId() {
        return triggerId;
    }

    public String getTriggerVector() {
        return triggerVector;
    }


    public Boolean getAutomatic() {
        return isAutomatic;
    }

    public Integer getPin() {
        return pin;
    }
}