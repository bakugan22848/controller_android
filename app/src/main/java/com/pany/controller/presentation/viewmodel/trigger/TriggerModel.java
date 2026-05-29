package com.pany.controller.presentation.viewmodel.trigger;

public class TriggerModel {
    String trigName;
    String trigId;
    Float trigVal;
    Float curVal;
    String type;
    Integer pin;

    public TriggerModel(String trigName, String trigId, Float curVal, Float trigVal, String type,Integer pin) {
        this.trigName = trigName;
        this.pin = pin;
        this.type = type;
        this.curVal = curVal;
        this.trigVal = trigVal;
        this.trigId = trigId;
    }


    public String getTrigName() {
        return trigName;
    }

    public String getTrigId() {
        return trigId;
    }


    public Float getTrigVal() {
        return trigVal;
    }

    public Float getCurVal() {
        return curVal;
    }

    public String getType() {
        return type;
    }

    public Integer getPin() {
        return pin;
    }
}
