package com.alerts.AlertTypes;

/**
 * Alert for too low blood oxygen saturation level
 * 
 * @author Yan
 */
public class SaturationLowAlert extends Alert {

    public SaturationLowAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
    
}
