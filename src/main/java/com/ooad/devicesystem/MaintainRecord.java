package com.ooad.devicesystem;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class MaintainRecord extends DomainObject{
    private Date date;
    private String record;
    private Person person;
    private int duration;
    private String planType;

    @ManyToOne
    private Device device;

    MaintainRecord(){

    }

    MaintainRecord(Date date, String record, Person person, int duration, String planType){
        this.date = date;
        this.record = record;
        this.person = person;
        this.duration = duration;
        this.planType = planType;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
}
