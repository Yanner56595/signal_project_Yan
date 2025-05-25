package com.alerts.AlertTypes;

import java.util.ArrayList;

import com.alerts.Alert;
import com.data_management.PatientRecord;

/**
 * Alert for abnormal data of ECG measurements
 * 
 * @author Yan
 */
public class ECGAbnormalAlert implements AlertCondition {

    /**
     * Triggers alerts if any extreme peaks in data exist
     * 
     * @param patientId String value of Id of a patient
     * @param records ArrayList of records of a patient to check
     * @return Alert if first found extreme peak in data or null
     */
    @Override
    public Alert evaluate(String patientId, ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> ECGRecords = filter.getFilteredRecords(
            records, "ECG");
        
        double average = average(ECGRecords);
        for (PatientRecord record : ECGRecords) {
            // Take abnormal as 1.5 times higher value than average
            if (record.getMeasurementValue() >= 1.5 * average) {
                return new Alert(
                    patientId, 
                    "Abnormal ECG", 
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
