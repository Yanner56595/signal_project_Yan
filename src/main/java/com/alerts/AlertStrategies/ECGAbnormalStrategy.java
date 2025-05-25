package com.alerts.AlertStrategies;

import java.util.ArrayList;

import com.alerts.AlertFactories.ECGAlertFactory;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

/**
 * Strategy to find abnormal data of ECG measurements
 * 
 * @author Yan
 */
public class ECGAbnormalStrategy implements AlertStrategy {

    ECGAlertFactory factory = new ECGAlertFactory();

    /**
     * Triggers alerts if any extreme peaks in data exist
     * 
     * @param records ArrayList of records of a patient to check
     * @return ECG alert if first found extreme peak in data or null
     */
    @Override
    public Alert checkCondition(ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> ECGRecords = filter.getFilteredRecords(
            records, "ECG");
        
        double average = average(ECGRecords);
        for (PatientRecord record : ECGRecords) {
            // Take abnormal as 1.5 times higher value than average
            if (record.getMeasurementValue() >= 1.5 * average) {
                return factory.createAlert(
                    String.valueOf(
                        record.getPatientId()),
                        "ECG", 
                        record.getTimestamp());
            }
        }
        return null;
    }
    
    /**
     * Calculates average of values in records given
     * 
     * @param records ArrayList of a patient's records
     * @return double average of values of given records
     */
    public double average(ArrayList<PatientRecord> records) {
        double sum = 0;
        for (int i = 0; i < records.size(); i++) {
            sum += records.get(i).getMeasurementValue();
        }
        return sum / records.size();
    }
}
