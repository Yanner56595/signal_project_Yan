package com.alerts.AlertTypes;

import java.util.ArrayList;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Alert for blood oxygen rapid drop
 * 
 * @author Yan
 */
public class SaturationDropAlert implements AlertCondition {

    /**
     * Triggers alert if there is a drop of 
     * 5% or more in 10 minutes
     * 
     * @param patientId String value of Id of a patient
     * @param records ArrayList of records of a patient to check
     * @return Alert if rapid drop exists or null
     */
    @Override
    public Alert evaluate(String patientId, ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> satRecords = filter.getFilteredRecords(
            records, "Saturation");
        
        for (int i = 0; i < satRecords.size() - 1; i++) {
            for (int j = i + 1; j < satRecords.size(); j++) {
                PatientRecord patientRecord1 = satRecords.get(i);
                PatientRecord patientRecord2 = satRecords.get(j);
                if (patientRecord2.getTimestamp() - patientRecord1.getTimestamp() <= 10 * 60000) {
                    if (patientRecord1.getMeasurementValue() - 
                                patientRecord2.getMeasurementValue() >= 5) {
                        return new Alert(
                            patientId, 
                            "Rapid Drop Alert", 
                            patientRecord2.getTimestamp());
                    }
                }
            }
        }
        return null;
    }
    
}
