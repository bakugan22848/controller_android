package com.pany.controller.presentation.viewmodel.notifications;

public class NotificationModel {
    String deviceName;
    String triggerName;


    public NotificationModel(String deviceName, String triggerName) {
        this.deviceName = deviceName;
        this.triggerName = triggerName;

    }


    public String getDeviceName() {
        return deviceName;
    }

    public String getTriggerName() {
        return triggerName;
    }


}
