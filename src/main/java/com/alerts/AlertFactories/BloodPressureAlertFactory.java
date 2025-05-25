package com.alerts.AlertFactories;

import com.alerts.AlertTypes.Alert;
import com.alerts.AlertTypes.BloodPressureThresholdAlert;
import com.alerts.AlertTypes.BloodPressureTrendAlert;
import com.alerts.AlertTypes.CombinedHypotensionHypoxiaAlert;

/**
 * Factory for blood pressure alerts
 * 
 * @author Yan
 */
public class BloodPressureAlertFactory extends AlertFactory {

    /**
     * Creates alert condition of blood pressure
     * 
     * @param patientId id of patient
     * @param condition string description of pattern we want
     * @param timestamp time
     * @return alert condition if such exists from condition or null
     */
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        switch (condition) {
            case "Trend":
                return new BloodPressureTrendAlert(patientId, 
                    "Blood Trend Alert", timestamp);
            case "Threshold":
                return new BloodPressureThresholdAlert(patientId, 
                    "Blood Threshold Alert", timestamp);
            case "HHA":
                return new CombinedHypotensionHypoxiaAlert(patientId, 
                    "Combined Hypotension Hypoxia Alert", timestamp);
            default:
                return null;
        }
    }
    
}
