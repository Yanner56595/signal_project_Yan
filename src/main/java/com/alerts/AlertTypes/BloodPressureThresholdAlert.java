package com.alerts.AlertTypes;

import java.util.ArrayList;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Class to check blood pressure for critical values
 * 
 * @author Yan
 */
public class BloodPressureThresholdAlert implements AlertCondition {

    /**
     * Triggers alert if blood preassure 
     * reaches critical values
     * 
     * @param patientId String value of Id of a patient
     * @param records ArrayList of records of a patient to check
     * @return either Alert instance in case of danger or null value
     */
    @Override
    public Alert evaluate(String patientId, ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> sysRecords = filter.getFilteredRecords(
            records, "SystolicPressure");
        ArrayList<PatientRecord> diaRecords = filter.getFilteredRecords(
            records, "DiastolicPressure");

        for (PatientRecord record : sysRecords) {
            double value = record.getMeasurementValue();
            if (value > 180 || value < 90) {
                return new Alert(
                    patientId, 
                    "Critical systolic pressure", 
                    record.getTimestamp());
            }
        }

        for (PatientRecord record : diaRecords) {
            double value = record.getMeasurementValue();
            if (value  > 120 || value < 60)
                return new Alert(
                    patientId, 
                    "Critical diastolic pressure", 
                    record.getTimestamp());
        }
        return null;
    }
    
}
