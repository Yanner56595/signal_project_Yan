package com.alerts.AlertStrategies;

import java.util.ArrayList;

import com.alerts.AlertFactories.BloodOxygenAlertFactory;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy for blood oxygen rapid drop
 * 
 * @author Yan
 */
public class SaturationDropStrategy implements AlertStrategy {

    BloodOxygenAlertFactory factory = new BloodOxygenAlertFactory();

    /**
     * Triggers alert if there is a drop of 
     * 5% or more in 10 minutes
     * 
     * @param records ArrayList of records of a patient to check
     * @return Saturation drop alert if rapid drop exists or null
     */
    @Override
    public Alert checkCondition(ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> satRecords = filter.getFilteredRecords(
            records, "Saturation");
        
        for (int i = 0; i < satRecords.size() - 1; i++) {
            for (int j = i + 1; j < satRecords.size(); j++) {
                PatientRecord patientRecord1 = satRecords.get(i);
                PatientRecord patientRecord2 = satRecords.get(j);
                if (patientRecord2.getTimestamp() - patientRecord1.getTimestamp() <= 10 * 60000) {
                    if (patientRecord1.getMeasurementValue() - 
                                patientRecord2.getMeasurementValue() >= 5) {
                        return factory.createAlert(
                            String.valueOf(patientRecord1.getPatientId()), 
                            "Drop", 
                            patientRecord2.getTimestamp());
                    }
                }
            }
        }
        return null;
    }
    
}
