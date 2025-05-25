package com.alerts.AlertTypes;

/**
 * Alert for Hypotensive Hypoxemia condition:
 * 
 * @author Yan
 */
public class CombinedHypotensionHypoxiaAlert extends Alert {

    public CombinedHypotensionHypoxiaAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }
    
}
