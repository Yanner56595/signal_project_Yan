package com.alerts.AlertTypes;

/**
 * Alert for problems with blood pressure
 * 
 * @author Yan
 */
public class BloodPressureTrendAlert extends Alert{

    public BloodPressureTrendAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
    
}
