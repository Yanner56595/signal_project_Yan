package com.alerts.AlertTypes;

import java.util.ArrayList;

import com.alerts.Alert;
import com.data_management.PatientRecord;
import com.data_management.PatientRecordFilter;

/**
 * Standard interface for all Alert checkers
 * 
 * @author Yan
 */
public interface AlertCondition {
    PatientRecordFilter filter = new PatientRecordFilter();

    /**
     * Evaluation of condition to call Alert if needed
     * 
     * @param patientId String value of Id of a patient
     * @param records ArrayList of records of a patient to check
     * @return Alert if conditions are satisfied or null
     */
    public Alert evaluate(String patientId, ArrayList<PatientRecord> records);
}
