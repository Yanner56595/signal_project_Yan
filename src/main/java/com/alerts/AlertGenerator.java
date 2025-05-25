package com.alerts;

import com.alerts.AlertTypes.AlertCondition;
import com.alerts.AlertTypes.AlertFactory;
import com.cardio_generator.outputs.OutputStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.ArrayList;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;
    private OutputStrategy outputStrategy;

    AlertFactory alertsFactory = new AlertFactory();

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     * @param outputStrategy strategy where the alerts will be sent
     */
    public AlertGenerator(DataStorage dataStorage, OutputStrategy outputStrategy) {
        this.dataStorage = dataStorage;
        this.outputStrategy = outputStrategy;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method.
     * 
     * The data for alert is checked for 10 previous minutes
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        //Evaluate data for last 10 minutes till current moment
        ArrayList<PatientRecord> records = patient.getRecords(
            System.currentTimeMillis() - 10 * 60000, 
            System.currentTimeMillis());
        
        ArrayList<AlertCondition> alertsToCheck = alertsFactory.loadAllAlerts();
        for (AlertCondition alertCheck : alertsToCheck) {
            Alert alert = alertCheck.evaluate(String.valueOf(patient.getId()), records);
            if (alert != null) {
                triggerAlert(alert);
            }
        }
    }

    /**
     * Triggers an alert for the monitoring system 
     * which is represented through OutputStrategy
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        outputStrategy.output(
            Integer.parseInt(alert.getPatientId()), 
            alert.getTimestamp(), "Alerts", 
            alert.getCondition());
    }
}
