package com.ooad.devicesystem;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MaintainPlan extends DomainObject{
    //todo planType 唯一

    private String planType;
    private int period;

    @ManyToOne
    private Device device;

    @Override
    public String toString() {
        return getId() + "\t" + getPeriod() + "\t" + getPlanType() + "\t" + getDevice().getId();
    }

    public MaintainPlan(){
        this.period = 0;
    }

    public MaintainPlan(String planType, int period){
        this.planType = planType;
        this.period = period;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
