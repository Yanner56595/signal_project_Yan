package com.alerts.AlertTypes;

import java.util.ArrayList;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Alert for too low blood oxygen saturation level
 * 
 * @author Yan
 */
public class SaturationLowAlert implements AlertCondition {

    /**
     * Creates alert if saturation level falls below 92%
     * 
     * @param patientId String value of Id of a patient
     * @param records ArrayList of records of a patient to check
     * @return alert if saturation level is too low or null
     */
    @Override
    public Alert evaluate(String patientId, ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> satRecords = filter.getFilteredRecords(
            records, "Saturation");
        for (PatientRecord record : satRecords) {
            if (record.getMeasurementValue() < 92) {
                return new Alert(
                    patientId, 
                    "Low Saturation", 
                    record.getTimestamp());
            }
        }
        return null;
    }
    
}
