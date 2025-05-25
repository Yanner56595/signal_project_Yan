package com.alerts.AlertTypes;

/**
 * Alert for Blood pressure critical values
 * 
 * @author Yan
 */
public class BloodPressureThresholdAlert extends Alert {

    public BloodPressureThresholdAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
    
}
