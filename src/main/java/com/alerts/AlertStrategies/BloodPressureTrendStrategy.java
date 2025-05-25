package com.alerts.AlertStrategies;

import java.util.ArrayList;

import com.alerts.AlertTypes.Alert;
import com.alerts.AlertFactories.BloodPressureAlertFactory;
import com.data_management.PatientRecord;

/**
 * Strategy to check blood pressure trends which 
 * may be dangerous for a patient
 * 
 * @author Yan
 */
public class BloodPressureTrendStrategy implements AlertStrategy {

    BloodPressureAlertFactory factory = new BloodPressureAlertFactory();

    /**
     * Triggers alert if patient's blood pressure 
     * shows consistent significant increase or 
     * decrease across 3 consecutive readings
     * 
     * @param records ArrayList of records of a patient to check
     * @return blood pressure trend alert or null
     */
    @Override
    public Alert checkCondition(ArrayList<PatientRecord> records) {
        ArrayList<PatientRecord> sysRecords = filter.getFilteredRecords(
            records, "SystolicPressure");
        ArrayList<PatientRecord> diaRecords = filter.getFilteredRecords(
            records, "DiastolicPressure");
        
        Object[] resultSys = findTrend(sysRecords);
        Object[] resultDia = findTrend(diaRecords);
        if (resultSys != null) {
                return factory.createAlert(
                    String.valueOf(records.get(0).getPatientId()), 
                    "Trend", 
                    (Long)resultSys[1]);
        }
        else if (resultDia != null) {
                return factory.createAlert(
                    String.valueOf(records.get(0).getPatientId()), 
                    "Trend", 
                    (Long)resultDia[1]);
        }
        return null;
    }

    /**
     * Finds trend in data where values difference for more than 10
     * 
     * @param records arraylist of patient records to analyze
     * @return null if there is no trend or array with String of name of trend 
     *         and timestamp when it happened
     */
    public Object[] findTrend(ArrayList<PatientRecord> records) {
        if (records.size() < 2) {
            return null;
        }
        double patientRecordValue1 = records.get(0).getMeasurementValue();
        double patientRecordValue2 = records.get(1).getMeasurementValue();
        for (int i = 2; i < records.size(); i++) {
            double patientRecordValue3 = records.get(i).getMeasurementValue();
            if (patientRecordValue2 - patientRecordValue1 > 10 && 
                    patientRecordValue3 - patientRecordValue2 > 10) {
                return new Object[] {"Increasing", records.get(i).getTimestamp()};
            }
            else if (patientRecordValue1 - patientRecordValue2 > 10 && 
                    patientRecordValue2 - patientRecordValue3 > 10) {
                return new Object[] {"Decreasing", records.get(i).getTimestamp()};
            }
            patientRecordValue1 = patientRecordValue2;
            patientRecordValue2 = patientRecordValue3;
        }
        return null;
    }
    
}
