package com.ooad.devicesystem;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class DeviceManagementSystemTest {

    @Autowired
    private PersistenceManager pm;

    @Test
    public void planTest(){
        PlanDescription pd=new PlanDescription("Test",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);

        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("Test"));
        pm.getCurrentSession().save(plan);

        MaintainPlan plan1 = pm.getCurrentSession().get(MaintainPlan.class, plan.getId());

        assertEquals(plan, plan1);
    }

    @Test
    public void planModifyTest(){
        PlanDescription pd=new PlanDescription("TypeTest",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        PlanDescriptionFactory.makePlan("TypeTest",50);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);

        assertEquals(plan.getPd().getPeriod(), 50);

    }

    @Test
    public void planAddTest(){
        PlanDescription pd=new PlanDescription("TypeTest",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);
        DeviceDescription dd1 = new DeviceDescription("test02","TT1","AUX-01");

        pm.getCurrentSession().save(dd1);

        Device d = new Device("here", dd1);
        d.addPlan(plan);

        pm.getCurrentSession().save(d);

        Device d2 = pm.getCurrentSession().get(Device.class, d.getId());

        assertEquals(d.getPlans(), d2.getPlans());

    }

    @Test
    public void planDeleteTest(){
        PlanDescription pd=new PlanDescription("TypeTest",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);
        DeviceDescription dd1 = new DeviceDescription("test02","TT1","AUX-01");

        pm.getCurrentSession().save(dd1);

        Device d = new Device("here", dd1);
        d.addPlan(plan);

        pm.getCurrentSession().save(d);

        Device d2 = pm.getCurrentSession().get(Device.class, d.getId());

        d2.deletePlan(d2.getPlans().get(0).getPd());
        pm.getCurrentSession().save(d2);

        Device d3 = pm.getCurrentSession().get(Device.class, d2.getId());

        assertEquals(d3.getPlans().size(),0);
    }

    @Test
    public void recordAddTest(){
        PlanDescription pd=new PlanDescription("TypeTest",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);
        DeviceDescription dd1 = new DeviceDescription("test02","TT1","AUX-01");
        pm.getCurrentSession().save(dd1);
        Device d = new Device("here", dd1);
        d.addPlan(plan);

        MaintainRecord r = new MaintainRecord(new Date(), "AAA", new Person("Andrew"), 90, "TypeTest");
        d.getPlanByType(r.getPlanType()).addRecord(r);
        pm.getCurrentSession().save(r);
        pm.getCurrentSession().save(d);

        Device d2 = pm.getCurrentSession().get(Device.class, d.getId());

        assertEquals(d2.getPlanByType("TypeTest").getRecords(), d.getPlanByType("TypeTest").getRecords());
    }

    @Test
    public void getRecentTaskTest(){
        PlanDescription pd=new PlanDescription("TypeTest",10);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);
        DeviceDescription dd1 = new DeviceDescription("test02","TT1","AUX-01");
        pm.getCurrentSession().save(dd1);
        Device d = new Device("here", dd1);
        d.addPlan(plan);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = new Date();
        Date testDate2= new Date();

        try {
            testDate = sdf.parse("2018-06-15");
            testDate2 = sdf.parse("2018-06-05");
        } catch (ParseException pe){
            pe.printStackTrace();
        }
        MaintainRecord r = new MaintainRecord(testDate, "AAA", new Person("Andrew"), 90, "TypeTest");
        MaintainRecord r2 = new MaintainRecord(testDate2, "BBB", new Person("White"), 15, "TypeTest");

        d.getPlanByType(r.getPlanType()).addRecord(r);
        d.getPlanByType(r2.getPlanType()).addRecord(r2);
        pm.getCurrentSession().save(r);
        pm.getCurrentSession().save(r2);
        pm.getCurrentSession().save(d);

        //testcase time = 15

        ArrayList<String> expect = new ArrayList<>();

        expect.add("2018-06-28");
        expect.add("2018-07-05");

        ArrayList<Date> test = d.getPlanByType("TypeTest").getRecentTask(15);
        ArrayList<String>  ttest = new ArrayList<>();

        for (Date date: test){
            ttest.add(sdf.format(date));
        }

        assertEquals(expect, ttest);

        // testcase time = 5 expect null
        Date testDate3 = new Date();
        try {
            testDate3 = sdf.parse("2018-06-25");
        } catch (ParseException e){
            e.printStackTrace();
        }
        MaintainRecord r3 = new MaintainRecord(testDate3, "BBB", new Person("White"), 15, "TypeTest");
        d.getPlanByType(r2.getPlanType()).addRecord(r3);
        pm.getCurrentSession().save(r3);
        pm.getCurrentSession().save(d);

        expect.clear();
        ttest.clear();

        test = d.getPlanByType("TypeTest").getRecentTask(5);

        for (Date date:test){
            ttest.add(sdf.format(date));
        }

        assertEquals(expect, ttest);

    }

    @Test
    public void testGetMaintainTime(){
        PlanDescription pd=new PlanDescription("TypeTest",10);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);
        DeviceDescription dd1 = new DeviceDescription("test02","TT1","AUX-01");
        pm.getCurrentSession().save(dd1);
        Device d = new Device("here", dd1);
        d.addPlan(plan);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = new Date();
        Date testDate2= new Date();

        try {
            testDate = sdf.parse("2018-06-15");
            testDate2 = sdf.parse("2018-06-05");
        } catch (ParseException pe){
            pe.printStackTrace();
        }
        MaintainRecord r = new MaintainRecord(testDate, "AAA", new Person("Andrew"), 90, "TypeTest");
        MaintainRecord r2 = new MaintainRecord(testDate2, "BBB", new Person("White"), 15, "TypeTest");

        d.getPlanByType(r.getPlanType()).addRecord(r);
        d.getPlanByType(r2.getPlanType()).addRecord(r2);
        pm.getCurrentSession().save(r);
        pm.getCurrentSession().save(r2);
        pm.getCurrentSession().save(d);

        int timeTypeTest = d.getPlanByType("TypeTest").getMaintainTime();

        assertEquals(timeTypeTest, 105);

    }

    @Test
    public void testGetTotalMaintainTime(){
        PlanDescription pd=new PlanDescription("TypeTest",10);
        PlanDescription pd2 = new PlanDescription("TypeTest2", 50);

        pm.getCurrentSession().save(pd);
        pm.getCurrentSession().save(pd2);
        PlanDescriptionFactory.makePlan(pd);
        PlanDescriptionFactory.makePlan(pd2);

        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        MaintainPlan plan2 = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest2"));

        pm.getCurrentSession().save(plan);
        pm.getCurrentSession().save(plan2);
        DeviceDescription dd1 = new DeviceDescription("test02","TT1","AUX-01");
        pm.getCurrentSession().save(dd1);
        Device d = new Device("here", dd1);
        d.addPlan(plan);
        d.addPlan(plan2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = new Date();
        Date testDate2= new Date();
        Date testDate3 = new Date();

        try {
            testDate = sdf.parse("2018-06-15");
            testDate2 = sdf.parse("2018-06-05");
            testDate3 = sdf.parse("2018-06-25");
        } catch (ParseException pe){
            pe.printStackTrace();
        }
        MaintainRecord r = new MaintainRecord(testDate, "AAA", new Person("Andrew"), 90, "TypeTest");
        MaintainRecord r2 = new MaintainRecord(testDate2, "BBB", new Person("White"), 15, "TypeTest");
        MaintainRecord r3 = new MaintainRecord(testDate3, "BBB", new Person("White"), 15, "TypeTest2");

        d.getPlanByType(r.getPlanType()).addRecord(r);
        d.getPlanByType(r2.getPlanType()).addRecord(r2);
        d.getPlanByType(r3.getPlanType()).addRecord(r3);

        pm.getCurrentSession().save(r);
        pm.getCurrentSession().save(r2);
        pm.getCurrentSession().save(r3);
        pm.getCurrentSession().save(d);

        int time;

        time = d.getPlanByType("TypeTest").getMaintainTime();

        assertEquals(time, 105);

        time = d.getPlanByType("TypeTest2").getMaintainTime();

        assertEquals(time, 15);

        time = d.getTotalMaintainTime();
        assertEquals(time, 120);

    }

    @Test
    public void testRegister(){
        DeviceDescription dd1 = new DeviceDescription("test01","TT1","AUX-01");
        pm.getCurrentSession().save(dd1);
        Device d = new Device("here", dd1);
        pm.getCurrentSession().save(d);

        DeviceDescription dd2 = new DeviceDescription("test02","TT1","AUX-02");
        pm.getCurrentSession().save(dd2);
        Device d2 = new Device("there", dd2);
        pm.getCurrentSession().save(d2);

        Register register = new Register();
        register.addDevice(d);
        register.addDevice(d2);

        pm.getCurrentSession().save(register);


        Register r2 = pm.getCurrentSession().get(Register.class, register.getId());

        assertEquals(register.getDevices(), r2.getDevices());
    }

}