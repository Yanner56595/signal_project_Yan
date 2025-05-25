package com.alerts.AlertTypes;

import java.util.ArrayList;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Alert for Hypotensive Hypoxemia condition:
 * Systolic blood pressure is below 90 mmHg
 * Blood oxygen saturation falls below 92%
 * 
 * @author Yan
 */
public class CombinedHypotensionHypoxiaAlert implements AlertCondition {

    /**
     * Trigers alert if there is Hypotensive Hypoxemia
     * 
     * @param patientId String value of Id of a patient
     * @param records ArrayList of records of a patient to check
     * @return alert if conditions for Hypotensive Hypoxemia are met or null
     */
    @Override
    public Alert evaluate(String patientId, ArrayList<PatientRecord> records) {
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
                return new Alert(
                    patientId, 
                    "Hypotensive Hypoxemia Alert", 
                    timestamp);
            }
        }
        return null;
    }
    
}
