package com.ooad.devicesystem;

import javax.persistence.Embeddable;

@Embeddable
public class DeviceType {
    private String deviceType;

    public DeviceType(){
        this.deviceType = "";
    }

    public DeviceType(String deviceType){
        this.deviceType = deviceType;
    }

    public String getDeviceType(){
        return this.deviceType;
    }


    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }



}
