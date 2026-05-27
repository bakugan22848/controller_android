package com.pany.controller.presentation.viewmodel.trigger;

public class TriggerModel {
    String trigName;
    String trigId;
    Float trigVal;
    Float curVal;

    public TriggerModel(String trigName, String trigId, Float trigVal, Float curVal) {
        this.trigName = trigName;
        this.trigId = trigId;
        this.trigVal = trigVal;
        this.curVal = curVal;
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
}
