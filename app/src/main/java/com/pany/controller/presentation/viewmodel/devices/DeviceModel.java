package com.pany.controller.presentation.viewmodel.devices;

public class DeviceModel {
    String deviceName;
    String deviceId;
    int connectPeriph;

    public DeviceModel(String deviceName, String deviceId, int connectPeriph) {
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.connectPeriph = connectPeriph;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getConnectPeriph() {
        return connectPeriph;
    }
}
