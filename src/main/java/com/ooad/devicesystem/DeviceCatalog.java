package com.ooad.devicesystem;

import java.util.ArrayList;

public class DeviceCatalog {

    private static ArrayList<DeviceDescription> catalog;

    public synchronized static ArrayList<DeviceDescription> getCatalog(){
        if (catalog == null)
            catalog = new ArrayList<>();
        return catalog;
    }

    public DeviceDescription getDescription(int id){
        return getCatalog().get(id);
    }

    public void removeDescription(int id){
        getCatalog().remove(id);
    }

    private boolean hasDescrption(DeviceDescription description){
        for (DeviceDescription d: catalog){
            if (d.getName().equals(description.getName()) && d.getModel().equals(description.getModel())
                    && d.getType().equals(description.getType())){
                return true;
            }
        }
        return false;
    }

    public boolean addDescription(DeviceDescription toAdd){
        if (!hasDescrption(toAdd)){
            getCatalog().add(toAdd);
            return true;
        }
        return false;
    }

}
