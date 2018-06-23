package com.ooad.devicesystem;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Device extends DomainObject{
    //todo deviceName 唯一
    private String name;
    private DeviceType type;
    private String typeSpecification;
    private Location location;

    @OneToMany(mappedBy = "device")
    private Collection<MaintainPlan> plan = new ArrayList<MaintainPlan>();

    @OneToMany(mappedBy = "device")
    private Collection<MaintainRecord> records = new ArrayList<>();


    public Device(){}
    public Device(String name, DeviceType type, String typeSpecification, Location location){
        this.name = name;
        this.type = type;
        this.typeSpecification = typeSpecification;
        this.location = location;
    }

    public void addMaintainPlan(MaintainPlan p){
        this.plan.add(p);
        p.setDevice(this);
    }

    public void addMaintainRecord(MaintainRecord r){
        this.records.add(r);
        r.setDevice(this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getTypeSpecification() {
        return typeSpecification;
    }

    public void setTypeSpecification(String typeSpecification) {
        this.typeSpecification = typeSpecification;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Collection<MaintainPlan> getMaintainPlan() {
        return plan;
    }

    public Collection<MaintainRecord> getMaintainRecords(){
        return records;
    }
}
