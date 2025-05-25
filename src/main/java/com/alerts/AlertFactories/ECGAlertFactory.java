package com.alerts.AlertFactories;

import com.alerts.AlertTypes.Alert;
import com.alerts.AlertTypes.ECGAlert;

/**
 * Factory of alerts for ECG
 * 
 * @author Yan
 */
public class ECGAlertFactory extends AlertFactory{

    /**
     * Creates alert condition of ECG
     * 
     * @param patientId id of patient
     * @param condition string description of pattern we want
     * @param timestamp time
     * @return alert condition if such exists from condition or null
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        switch (condition) {
            case "ECG":
                return new ECGAlert(patientId, "ECG Alert", timestamp);
            default:
                return null;
        }
    }
    
}
