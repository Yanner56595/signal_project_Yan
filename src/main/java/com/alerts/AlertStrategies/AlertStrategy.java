package com.alerts.AlertStrategies;

import java.util.ArrayList;

import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;
import com.data_management.PatientRecordFilter;

/**
 * Strategy to evaluate conditions for alerts
 * 
 * @author Yan
 */
public interface AlertStrategy {
    PatientRecordFilter filter = new PatientRecordFilter();

    /**
     * Evaluation of condition to call Alert if needed
     * 
     * @param records ArrayList of records of a patient to check
     * @return Alert if conditions are satisfied or null
     */
    Alert checkCondition(ArrayList<PatientRecord> records);
}
