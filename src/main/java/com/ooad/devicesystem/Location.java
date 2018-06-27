package com.ooad.devicesystem;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private String location;

    public Location(){

    }

    public Location(String location){
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
