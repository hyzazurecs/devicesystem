package com.ooad.devicesystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeviceDescription {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    public int getId(){
        return id;
    }

    private String name;
    private String type;
    private String model;



    DeviceDescription(){

    }

    DeviceDescription(String name, String type, String model){
        this.name = name;
        this.type = type;
        this.model = model;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
