package com.pany.controller.data.models;

import com.google.gson.annotations.SerializedName;

public class ControllerUpdate{
    @SerializedName("name")
    private String name;

    @SerializedName("trigger_id")
    private String triggerId;

    @SerializedName("triggerVector")
    private String triggerVector;

    @SerializedName("trigger_value")
    private Integer triggerValue;

    @SerializedName("last_state")
    private Boolean lastState;

    @SerializedName("is_automatic")
    private Boolean isAutomatic;


    public ControllerUpdate(String name, String triggerId, String triggerVector, Integer triggerValue, Boolean lastState, Boolean isAutomatic) {
        this.name = name;
        this.triggerId = triggerId;
        this.triggerVector = triggerVector;
        this.triggerValue = triggerValue;
        this.lastState = lastState;
        this.isAutomatic = isAutomatic;
    }

    public String getName() {
        return name;
    }

    public Integer getTriggerValue() {
        return triggerValue;
    }

    public Boolean getLastState() {
        return lastState;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public String getTrigger_vector() {
        return trigger_vector;
    }

    public Boolean getAutomatic() {
        return isAutomatic;
    }
}
