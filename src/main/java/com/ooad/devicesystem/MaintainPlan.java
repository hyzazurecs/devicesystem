package com.ooad.devicesystem;


import javax.persistence.*;
import java.util.*;

@Entity
public class MaintainPlan extends DomainObject {

    @ManyToOne
    private PlanDescrption pd;

    @OneToMany
    private List<MaintainRecord> records = new ArrayList<>();

    public MaintainPlan() {

    }

    public MaintainPlan(PlanDescrption pd) {
        this.pd = pd;
    }

    public PlanDescrption getPd() {
        return PlanDescriptionFactory.getPlanDescription(pd.getPlanType());

    }

    public List<MaintainRecord> getRecords(){
        return this.records;
    }

    public void setPd(PlanDescrption pd) {
        this.pd = pd;
    }

    public void addRecord(MaintainRecord record){
        this.records.add(record);
    }

    public int getMaintainTime(){
        int time = 0;
        for(MaintainRecord record:records){
            time += record.getDuration();
        }
        return time;
    }

    public ArrayList<Date> getRecentTask(int time){
        ArrayList<Date> tasks = new ArrayList<>();

        Date date1 = addTime(getLastTime(), this.getPd().getPeriod());
        Date current = new Date();
        Date date2 =addTime(current,time);
        while(!date1.after(date2)){
            if (!date1.before(current)){
                tasks.add(date1);
            } else{
                tasks.add(current);
            }
            date1=addTime(date1,this.getPd().getPeriod());
        }

        return tasks;
    }

    private Date addTime(Date date ,int time){
        Calendar c =Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,time);
        return c.getTime();

    }

    public Date getLastTime(){

        if (records.size() == 0) return new Date();

        records.sort(new Comparator<MaintainRecord>() {
            @Override
            public int compare(MaintainRecord o1, MaintainRecord o2) {
                return o1.getDate().before(o2.getDate()) ? 1 : -1;
            }
        });

        return records.get(0).getDate();
    }

}


