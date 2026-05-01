package com.pany.controller.presentation.viewmodel.controller;

public class ControllerModel {
    String contrName;
    String contrId;
    String aimTrig;
    boolean state;

    public void setState(boolean state) {
        this.state = state;
    }

    public ControllerModel(String contrName, String contrId, String aimTrig, boolean state) {
        this.contrName = contrName;
        this.contrId = contrId;
        this.aimTrig = aimTrig;
        this.state = state;
    }

    public String getContrName() {
        return contrName;
    }

    public String getContrId() {
        return contrId;
    }

    public String getAimTrig() {
        return aimTrig;
    }

    public boolean isState() {
        return state;
    }
}
