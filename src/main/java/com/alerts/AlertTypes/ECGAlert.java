package com.alerts.AlertTypes;

/**
 * Alert for abnormal data of ECG measurements
 * 
 * @author Yan
 */
public class ECGAlert extends Alert {

    public ECGAlert(String patientId, String condition, long timestamp) {
        super(patientId, condition, timestamp);
    }

}
