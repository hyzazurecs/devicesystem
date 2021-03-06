package com.ooad.devicesystem;

import javax.persistence.*;

@Entity
public class PlanDescription extends DomainObject{

    @Column(unique = true)
    private String planType;
    private int period;

    PlanDescription(){
        this.planType = "";
        this.period = 10;
    }


    PlanDescription(String planType, int period){
        this.planType=planType;
        this.period=period;

    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

}
