package com.ooad.devicesystem;

import javax.persistence.*;
import java.util.*;

@Entity
public class Device{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    public int getId(){
        return id;
    }

    private String location;

    @ManyToOne
    private DeviceDescription description;

    @OneToMany
    private List<MaintainPlan> plans = new ArrayList<MaintainPlan>();


    Device(String location, DeviceDescription description){
        this.location = location;
        this.description = description;
    }

    public void addPlan(MaintainPlan plan){
        this.plans.add(plan);
    }

    public void deletePlan(PlanDescrption pd){
        for (MaintainPlan p: plans){
            if (p.getPd().equals(pd)){
                plans.remove(p);
                return;
            }
        }
    }


    public List<MaintainPlan> getPlans(){
        return this.plans;
    }

    public MaintainPlan getPlanByType(String type){
        for (MaintainPlan p: plans){
            if (p.getPd().getPlanType().equals(type))
                return p;
        }
        return null;
    }


    public int getTotalMaintainTime(){
        int time = 0;
        for(MaintainPlan plan :plans) {
            time+=plan.getMaintainTime();
        }

        return time;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DeviceDescription getDescription() {
        return description;
    }

    public void setDescription(DeviceDescription description) {
        this.description = description;
    }
}
