package com.alerts.AlertStrategies;

import java.util.ArrayList;

import com.alerts.AlertFactories.BloodPressureAlertFactory;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy for Hypotensive Hypoxemia condition:
 * Systolic blood pressure is below 90 mmHg
 * Blood oxygen saturation falls below 92%
 * 
 * @author Yan
 */
public class CombinedHypotensionHypoxiaStrategy implements AlertStrategy {

    BloodPressureAlertFactory factory = new BloodPressureAlertFactory();

    /**
     * Trigers alert if there is Hypotensive Hypoxemia
     * 
     * @param records ArrayList of records of a patient to check
     * @return CHH alert if conditions are met or null
     */
    @Override
    public Alert checkCondition(ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> sysRecords = filter.getFilteredRecords(
            records, "SystolicPressure");
        ArrayList<PatientRecord> satRecords = filter.getFilteredRecords(
            records, "Saturation");
        
        // Assume measurements are taken at the same 
        // time for both conditions
        for (int i = 0; i < sysRecords.size(); i++) {
            double sysPressure = sysRecords.get(i).getMeasurementValue();
            double satLevel = satRecords.get(i).getMeasurementValue();
            long timestamp = sysRecords.get(i).getTimestamp();
            if (sysPressure < 90 && satLevel < 92) {
                return factory.createAlert(
                    String.valueOf(sysRecords.get(i).getPatientId()), 
                    "HHA", 
                    timestamp);
            }
        }
        return null;
    }
    
}
