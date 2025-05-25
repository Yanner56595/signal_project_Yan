package com.alerts.AlertTypes;

/**
 * Manual Alert called by nurses
 * 
 * @author Yan
 */
public class TriggeredButtonAlert extends Alert {

    public TriggeredButtonAlert(String patientId, String condition, long timestamp) {
        super(patientId, "Attention: " + condition, timestamp);
    }
    
}
