package com.ooad.devicesystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class Example {
    private static final Logger log = LoggerFactory.getLogger(Example.class);

    private SessionFactory sessionFactory;
    public Example( SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void initTestData3() throws Exception{
        Device d1 = new Device("A0123", new DeviceType("Air Conditioner12"), "SHABI-0001", new Location("PVG-03"));
        Device d2 = new Device("A0123", new DeviceType("Air Conditioner123"), "SHABI-0001", new Location("PVG-03"));

        getCurrentSession().save(d1);
//        getCurrentSession().save(d2);
    }

    public void initTestData() throws Exception{
        Device d = new Device("A0123", new DeviceType("Air Conditioner"), "SHABI-0001", new Location("PVG-03"));
        MaintainPlan p1 = new MaintainPlan("type1", 60);
        MaintainPlan p2 = new MaintainPlan("type2",30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        MaintainRecord r1 = new MaintainRecord(sdf.parse("2018-06-01"), "啥也没做", new Person("张三"), 1, "type1");

        d.addMaintainPlan(p1);
        d.addMaintainPlan(p2);

        d.addMaintainRecord(r1);

        getCurrentSession().save(d);
        getCurrentSession().save(p1);
        getCurrentSession().save(p2);
        getCurrentSession().save(r1);

    }

    public void initTest2Data() throws Exception{
        Device d = new Device("A02333", new DeviceType("Refrigerator"), "SHABI-0001", new Location("PVG-01"));
        MaintainPlan p1 = new MaintainPlan("type1", 60);
        MaintainPlan p2 = new MaintainPlan("type2",30);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        MaintainRecord r1 = new MaintainRecord(sdf.parse("2018-05-01"), "啥也没做", new Person("张三"), 1, "type2");
        MaintainRecord r2 = new MaintainRecord(sdf.parse("2018-06-01"), "啥也没做", new Person("李四"), 100, "type2");
        MaintainRecord r3 = new MaintainRecord(sdf.parse("2018-07-01"), "啥也没做", new Person("王五"), 233, "type2");

        MaintainRecord r4 = new MaintainRecord(sdf.parse("2018-07-01"), "啥也没做", new Person("王五"), 60, "type1");


        d.addMaintainPlan(p1);
        d.addMaintainPlan(p2);

        d.addMaintainRecord(r1);
        d.addMaintainRecord(r2);

        d.addMaintainRecord(r3);
        d.addMaintainRecord(r4);


        getCurrentSession().save(d);
        getCurrentSession().save(p1);
        getCurrentSession().save(p2);
        getCurrentSession().save(r1);
        getCurrentSession().save(r2);
        getCurrentSession().save(r3);
        getCurrentSession().save(r4);


    }

    public void demo() throws Exception{
//        initTestData();

//        ArrayList<MaintainPlan> plans = getAllMaintenanceTasksByID(61L);
//
//        for (MaintainPlan p: plans){
//            log.info(p.toString());
//        }


//        initTest2Data();
//        int time = countDurationByType(65L, "type2");
//        int time2 = countDurationByDevice(65L);
//        log.info(time2 + "");

        initTestData3();
    }

    public int countDurationByDevice(long deviceID){
        int ret = 0;
        Device device = getCurrentSession().get(Device.class, deviceID);

        Collection<MaintainPlan> plans = device.getMaintainPlan();

        for (MaintainPlan p: plans){
            ret += countDurationByType(deviceID, p.getPlanType());
        }

        return ret;
    }

    public int countDurationByType(long deviceID, String type){
        int ret = 0;
        Device device = getCurrentSession().get(Device.class, deviceID);

        Collection<MaintainRecord> records = device.getMaintainRecords();

        for (MaintainRecord r: records){
            if (r.getPlanType().equals(type)){
                ret += r.getDuration();
            }
        }

        return ret;
    }


    public ArrayList<MaintainPlan> getAllMaintenanceTasksByID(long id) throws Exception{
        ArrayList<MaintainPlan> ret = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date now = new Date();
        // todo USED FOR TEST
        now = sdf.parse("2018-07-25");

        Device device = getCurrentSession().get(Device.class, id);

        Collection<MaintainPlan> plans = device.getMaintainPlan();
        Collection<MaintainRecord> records = device.getMaintainRecords();

        Map<String, MaintainPlan> planMap = new HashMap<>();

        for (MaintainPlan p: plans){
            planMap.put(p.getPlanType(), p);
        }

        for (MaintainRecord r: records){
            long diff = (now.getTime() - r.getDate().getTime())/(1000*3600*24);

            String type = r.getPlanType();
            int period = planMap.get(type).getPeriod();

//            log.info(diff + " " + period);
            if (period - diff <= 10){
                ret.add(planMap.get(type));
                planMap.remove(type);
            }
        }

        // 没有保养记录的
        if (planMap.size() > 0){
            for (String type: planMap.keySet()){
                ret.add(planMap.get(type));
            }
        }

        return ret;

    }

}
