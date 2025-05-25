package com.alerts.AlertStrategies;

import java.util.ArrayList;

import com.alerts.AlertFactories.BloodOxygenAlertFactory;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy for too low blood oxygen saturation level
 * 
 * @author Yan
 */
public class SaturationLowStrategy implements AlertStrategy {

    BloodOxygenAlertFactory factory = new BloodOxygenAlertFactory();

    /**
     * Creates alert if saturation level falls below 92%
     * 
     * @param records ArrayList of records of a patient to check
     * @return Saturation low alert if saturation level is too low or null
     */
    @Override
    public Alert checkCondition(ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> satRecords = filter.getFilteredRecords(
            records, "Saturation");
        for (PatientRecord record : satRecords) {
            if (record.getMeasurementValue() < 92) {
                return factory.createAlert(
                    String.valueOf(record.getPatientId()), 
                    "Low", 
                    record.getTimestamp());
            }
        }
        return null;
    }
    
}
