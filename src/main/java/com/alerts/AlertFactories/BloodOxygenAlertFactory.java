package com.alerts.AlertFactories;

import com.alerts.AlertTypes.Alert;
import com.alerts.AlertTypes.SaturationDropAlert;
import com.alerts.AlertTypes.SaturationLowAlert;

/**
 * Factory for alerts connected to blood oxygen problems
 * 
 * @author Yan
 */
public class BloodOxygenAlertFactory extends AlertFactory {

    /**
     * Creates alert condition of blood oxygen
     * 
     * @param patientId id of patient
     * @param condition string description of pattern we want
     * @param timestamp time
     * @return alert condition if such exists from condition or null
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        switch (condition) {
            case "Low": 
                return new SaturationLowAlert(patientId, 
                    "Low saturation", timestamp);
            case "Drop": 
                return new SaturationDropAlert(patientId, 
                    "Drop saturation", timestamp);
            default:
                return null;
        }
    }
}
