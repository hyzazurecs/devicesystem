package com.ooad.devicesystem;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class DeviceManagementSystemTest {
    private static final Logger log = LoggerFactory.getLogger(Example.class);

    @Autowired
    private PersistenceManager pm;

    @Test
    public void planTest(){
        PlanDescrption pd=new PlanDescrption("Test",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);

        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("Test"));
        pm.getCurrentSession().save(plan);

        MaintainPlan plan1 = pm.getCurrentSession().get(MaintainPlan.class, plan.getId());

        assertEquals(plan, plan1);
    }

    @Test
    public void planModifyTest(){
        PlanDescrption pd=new PlanDescrption("TypeTest",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        PlanDescriptionFactory.makePlan("TypeTest",50);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);

        assertEquals(plan.getPd().getPeriod(), 50);

    }

    @Test
    public void planAddTest(){
        PlanDescrption pd=new PlanDescrption("TypeTest",100);
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
        PlanDescrption pd=new PlanDescrption("TypeTest",100);
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
        PlanDescrption pd=new PlanDescrption("TypeTest",100);
        pm.getCurrentSession().save(pd);
        PlanDescriptionFactory.makePlan(pd);
        MaintainPlan plan = new MaintainPlan(PlanDescriptionFactory.getPlanDescription("TypeTest"));
        pm.getCurrentSession().save(plan);
        DeviceDescription dd1 = new DeviceDescription("test02","TT1","AUX-01");

        pm.getCurrentSession().save(dd1);

        Device d = new Device("here", dd1);
        d.addPlan(plan);

        MaintainRecord r = new MaintainRecord(new Date(), "AAA", new Person("Andrew"), 90, "TypeTest");
        d.addRecord(r);
        pm.getCurrentSession().save(r);

        pm.getCurrentSession().save(d);

        Device d2 = pm.getCurrentSession().get(Device.class, d.getId());

        assertEquals(d2.getRecords(), d.getRecords());
    }





}