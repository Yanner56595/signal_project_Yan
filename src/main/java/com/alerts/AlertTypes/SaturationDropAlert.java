package com.alerts.AlertTypes;

/**
 * Alert for saturation drop
 * 
 * @author Yan
 */
public class SaturationDropAlert extends Alert {

    public SaturationDropAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
    
}
