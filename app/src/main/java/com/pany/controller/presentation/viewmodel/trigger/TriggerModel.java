package com.pany.controller.presentation.viewmodel.trigger;

public class TriggerModel {
    String trigName;
    String trigId;
    String aimContr;
    int trigVal;
    int curVal;

    public TriggerModel(String trigName, String trigId, String aimContr, int trigVal, int curVal) {
        this.trigName = trigName;
        this.trigId = trigId;
        this.aimContr = aimContr;
        this.trigVal = trigVal;
        this.curVal = curVal;
    }

    public String getTrigName() {
        return trigName;
    }

    public String getTrigId() {
        return trigId;
    }

    public String getAimContr() {
        return aimContr;
    }

    public int getTrigVal() {
        return trigVal;
    }

    public int getCurVal() {
        return curVal;
    }
}
