package com.ooad.devicesystem;

import com.sun.prism.impl.Disposer;
import sun.applet.Main;

import javax.persistence.*;
import java.text.Format;
import java.text.SimpleDateFormat;
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

    @OneToMany
    private List<MaintainRecord> records = new ArrayList<MaintainRecord>();

    Device(String location, DeviceDescription description){
        this.location = location;
        this.description = description;
    }

    public void addPlan(MaintainPlan plan){
        this.plans.add(plan);
    }

    public void addRecord(MaintainRecord record){
        this.records.add(record);
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

    public List<MaintainRecord> getRecords() {
        return records;
    }

    //    public ArrayList<Task> getRecentTask(int time){
//        ArrayList<Task> tasks=new ArrayList<>();
//        for(MaintainPlan plan : plans){
//
//            Date date1 =addTime(getLastTime(plan),time);
//            Date current =new Date();
//            Date date2 =addTime(current,time);
//            if (!date1.before(current) &&!date1.after(date2)){
//                tasks.add(new Task(plan.getPlanType(),date1));
//            }
//
//        }
//        return tasks;
//    }
//    public int getTotalMaintainTime(){
//        int time =0;
//        for(MaintainPlan plan :plans) {
//
//            time+=plan.getMaintainTIme();
//        }
//
//        return time;
//    }
//
//    public int getTypeMaintainTIme(MaintainPlan plan){
//        int time=0;
//        for(MaintainRecord record:records){
//            if(record.getPlanType()==plan.getPlanType()){
//                time+=record.getDuration();
//            }
//        }
//        return time;
//    }
//    private Date addTime(Date date ,int time){
//        Calendar c =Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.DAY_OF_MONTH,time);
//        return c.getTime();
//
//    }
//
//    private Date getLastTime(MaintainPlan plan){
//        Date date =addTime(new Date(),-plan.getPeriod());
//        for(MaintainRecord record: records){
//            if(record.getPlanType()==plan.getPlanType()&& date.before(record.getDate())){
//                date=record.getDate();
//            }
//        }
//
//        return date;
//    }



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
