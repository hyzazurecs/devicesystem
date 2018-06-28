package com.ooad.devicesystem;

import java.util.ArrayList;

public class Register {
    ArrayList<Device> devices=new ArrayList<>();
    Register(){

    }
    Register(ArrayList<Device> devices){
        this.devices=devices;
    }
    public void addDevice(Device device){
        devices.add(device);
    }
    public void removeDevice(Device device){

        devices.remove(device);
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }
}
