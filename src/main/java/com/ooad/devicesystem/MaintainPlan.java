package com.ooad.devicesystem;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class MaintainPlan extends DomainObject{

    private String planType;
    private int period;

    @Override
    public String toString() {
        return getId() + "\t" + getPeriod() + "\t" + getPlanType();
    }

    public MaintainPlan(){

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

}
