package com.pany.controller.presentation.viewmodel.controller;

public class ControllerModel {
    String contrName;
    String contrId;
    String triggerId;
    Integer trigVal;
    Boolean state;
    String triggerVector;
    Boolean isAutomatic;


    public ControllerModel(String contrName, String contrId, String triggerId, Integer trigVal, Boolean state, String triggerVector, Boolean isAutomatic) {
        this.contrName = contrName;
        this.contrId = contrId;
        this.triggerId = triggerId;
        this.trigVal = trigVal;
        this.state = state;
        this.triggerVector = triggerVector;
        this.isAutomatic = isAutomatic;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    public void setAutomatic(Boolean automatic) {isAutomatic = automatic;}

    public String getContrName() {
        return contrName;
    }

    public String getContrId() {
        return contrId;
    }

    public Integer getTrigVal() {
        return trigVal;
    }

    public Boolean isState() {
        return state;
    }

    public Boolean getAutomatic() {return isAutomatic;}

    public String getTriggerVector() {return triggerVector;}

    public String getTriggerId() {return triggerId;}
}
