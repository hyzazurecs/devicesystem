package com.ooad.devicesystem;

import java.util.HashMap;

public class PlanDescriptionFactory {
    private static final HashMap<String,PlanDescrption> pdMap=new HashMap<>();
    public synchronized static PlanDescrption getPlanDescription(String type){
        PlanDescrption pd =(PlanDescrption) pdMap.get(type);
        if(pd==null){
             pd=new PlanDescrption(type,10);
             makePlan(pd);
        }
        return pd;
    }
    public static void makePlan(PlanDescrption pd){
        pdMap.put(pd.getPlanType(),pd);
    }

    public static void makePlan(String type, int period) {
        pdMap.get(type).setPeriod(period);
    }
}