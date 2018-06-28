package com.ooad.devicesystem;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
public class MaintainPlan extends DomainObject {

    @ManyToOne
    private PlanDescrption pd;



    public MaintainPlan() {

    }

    public MaintainPlan(PlanDescrption pd) {
        this.pd = pd;
    }

    public PlanDescrption getPd() {
        return PlanDescriptionFactory.getPlanDescription(pd.getPlanType());

    }

    public void setPd(PlanDescrption pd) {
        this.pd = pd;
    }
//    public void addRecord(MaintainRecord record){
//        this.records.add(record);
//    }
//    public int getMaintainTIme(){
//        int time=0;
//        for(MaintainRecord record:records){
//
//                time+=record.getDuration();
//        }
//        return time;
//    }
//    public ArrayList<Date> getRecentTask(int time){
//        ArrayList<Date> tasks=new ArrayList<>();
//
//
//            Date date1 =addTime(getLastTime(),this.getPd().getPeriod());
//            Date current =new Date();
//            Date date2 =addTime(current,time);
//            while(!date1.after(date2)){
//                if (!date1.before(current)){
//                    tasks.add(date1);
//                }
//                date1=addTime(date1,this.getPd().getPeriod());
//            }
//
//
//        return tasks;
//    }
    private Date addTime(Date date ,int time){
        Calendar c =Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,time);
        return c.getTime();

    }

//    private Date getLastTime(){
//        Date date =addTime(new Date(),-getPd().getPeriod());
//        for(MaintainRecord record: records){
//            if(date.before(record.getDate())){
//                date=record.getDate();
//            }
//        }
//
//        return date;
//    }

}


