package com.alerts.AlertTypes;

import java.util.ArrayList;

/**
 * Factory of all alerts to check
 * 
 * @author Yan
 */
public class AlertFactory {
    
    /**
     * Creates instances of all alerts to check
     * 
     * @return arraylist with all alert conditions
     */
    public ArrayList<AlertCondition> loadAllAlerts() {
        ArrayList<AlertCondition> allAlerts = new ArrayList<AlertCondition>();
        
        allAlerts.add(new BloodPressureTrendAlert());
        allAlerts.add(new BloodPressureThresholdAlert());
        allAlerts.add(new CombinedHypotensionHypoxiaAlert());
        allAlerts.add(new ECGAbnormalAlert());
        allAlerts.add(new SaturationDropAlert());
        allAlerts.add(new SaturationLowAlert());

        return allAlerts;
    }
}
