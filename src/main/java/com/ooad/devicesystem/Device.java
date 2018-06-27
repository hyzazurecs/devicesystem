package com.ooad.devicesystem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Device{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    public int getId(){
        return id;
    }

    private Location location;

    @ManyToOne
    private DeviceDescription description;

    private ArrayList<MaintainPlan> plans = new ArrayList<>();

    private ArrayList<MaintainRecord> records = new ArrayList<>();

    Device(Location location, DeviceDescription description){
        this.location = location;
        this.description = description;
    }

    public void addPlan(MaintainPlan plan){
        this.plans.add(plan);
    }

    public void addRecord(MaintainRecord record){
        this.records.add(record);
    }

    public void deletePlan(String type){
        for (MaintainPlan p: plans){
            if (p.getPlanType().equals(type)){
                plans.remove(p);
                return;
            }
        }
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DeviceDescription getDescription() {
        return description;
    }

    public void setDescription(DeviceDescription description) {
        this.description = description;
    }
}
