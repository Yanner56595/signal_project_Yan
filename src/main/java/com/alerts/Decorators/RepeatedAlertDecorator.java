package com.alerts.Decorators;

import java.util.ArrayList;

import com.alerts.AlertStrategies.AlertStrategy;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

/**
 * Decorator to recheck conditions over time
 * 
 * @author Yan
 */
public class RepeatedAlertDecorator {
    
    private AlertStrategy strategy;
    private int repeatCount;
    private long intervalMillis;

    /**
     * Creates decorator to repeat checks
     * 
     * @param strategy conditions to check
     * @param repeatCount amount of future checks
     * @param intervalMillis interval of checks
     */
    public RepeatedAlertDecorator(AlertStrategy strategy, int repeatCount, long intervalMillis) {
        this.strategy = strategy;
        this.repeatCount = repeatCount;
        this.intervalMillis = intervalMillis;
    }

    /**
     * Checks records over time
     * 
     * @param records records of a patient
     * @return Alert if there is one or null
     */
    public Alert checkAlert(ArrayList<PatientRecord> records) {
        for (int i = 0; i < repeatCount; i++) {
            Alert alert = strategy.checkCondition(records);
            if (alert == null) {
                return alert;
            }

            try {
                // Simulating delay
                Thread.sleep(intervalMillis); 
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null; // Nothing was triggered after repeats
    }
}