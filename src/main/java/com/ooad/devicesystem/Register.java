package com.ooad.devicesystem;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Register extends DomainObject{

    @OneToMany
    private List<Device> devices=new ArrayList<>();

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

    public List<Device> getDevices() {
        return devices;
    }
}
