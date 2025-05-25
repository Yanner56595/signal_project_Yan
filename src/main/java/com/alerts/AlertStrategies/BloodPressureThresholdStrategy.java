package com.alerts.AlertStrategies;

import java.util.ArrayList;

import com.alerts.AlertFactories.BloodPressureAlertFactory;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy to check blood pressure for critical values
 * 
 * @author Yan
 */
public class BloodPressureThresholdStrategy implements AlertStrategy {

    BloodPressureAlertFactory factory = new BloodPressureAlertFactory();

    /**
     * Triggers alert if blood preassure 
     * reaches critical values
     * 
     * @param records ArrayList of records of a patient to check
     * @return blood pressure threshold alert or null
     */
    @Override
    public Alert checkCondition(ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> sysRecords = filter.getFilteredRecords(
            records, "SystolicPressure");
        ArrayList<PatientRecord> diaRecords = filter.getFilteredRecords(
            records, "DiastolicPressure");

        for (PatientRecord record : sysRecords) {
            double value = record.getMeasurementValue();
            if (value > 180 || value < 90) {
                return factory.createAlert(
                    String.valueOf(record.getPatientId()), 
                    "Threshold", 
                    record.getTimestamp());
            }
        }

        for (PatientRecord record : diaRecords) {
            double value = record.getMeasurementValue();
            if (value  > 120 || value < 60)
                return factory.createAlert(
                    String.valueOf(record.getPatientId()), 
                    "Threshold", 
                    record.getTimestamp());
        }
        return null;
    }
    
}
