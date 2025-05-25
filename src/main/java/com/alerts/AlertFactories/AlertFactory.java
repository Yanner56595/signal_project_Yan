package com.alerts.AlertFactories;

import com.alerts.AlertTypes.Alert;

/**
 * Abstract factory for production alert conditions 
 * based on user's input.
 * 
 * @author Yan
 */
public abstract class AlertFactory {
    
    /**
     * Constructor of Alert condition
     * 
     * @param patientId id of patient
     * @param condition string description of pattern we want
     * @param timestamp time
     * @return alert if such exists from condition or null
     */
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}
