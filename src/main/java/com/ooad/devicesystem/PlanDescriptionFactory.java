package com.ooad.devicesystem;

import java.util.HashMap;

public class PlanDescriptionFactory {
    private static final HashMap<String,PlanDescription> pdMap=new HashMap<>();
    public synchronized static PlanDescription getPlanDescription(String type){
        PlanDescription pd =(PlanDescription) pdMap.get(type);
        if(pd==null){
             pd=new PlanDescription(type,10);
             makePlan(pd);
        }
        return pd;
    }
    public static void makePlan(PlanDescription pd){
        pdMap.put(pd.getPlanType(),pd);
    }

    public static void makePlan(String type, int period) {
        pdMap.get(type).setPeriod(period);
    }
}