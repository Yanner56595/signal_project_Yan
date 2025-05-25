package com.data_management;

import com.alerts.AlertTypes.Alert;

/**
 * Represents a single record of patient data at a specific point in time.
 * This class stores all necessary details for a single observation or
 * measurement
 * taken from a patient, including the type of record (such as ECG, blood
 * pressure),
 * the measurement value, and the exact timestamp when the measurement was
 * taken.
 */
public class PatientRecord {
    private int patientId;
    private String recordType; // Example: ECG, blood pressure, etc.
    private double measurementValue; // Example: heart rate
    private long timestamp;

    /**
     * Constructs a new patient record with specified details.
     * 
     * @param patientId        the unique identifier for the patient
     * @param measurementValue the numerical value of the recorded measurement
     * @param recordType       the type of measurement (e.g., "ECG", "Blood
     *                         Pressure")
     * @param timestamp        the time at which the measurement was recorded, in
     *                         milliseconds since epoch
     */
    public PatientRecord(int patientId, double measurementValue, String recordType, long timestamp) {
        this.patientId = patientId;
        this.measurementValue = measurementValue;
        this.recordType = recordType;
        this.timestamp = timestamp;
    }

    /**
     * Returns the patient ID associated with this record.
     * 
     * @return the patient ID
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Returns the measurement value of this record.
     * 
     * @return the measurement value
     */
    public double getMeasurementValue() {
        return measurementValue;
    }

    /**
     * Returns the timestamp when this record was taken.
     * 
     * @return the timestamp in milliseconds since epoch
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the type of record (e.g., "ECG", "Blood Pressure").
     * 
     * @return the record type
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * Checks for equality of records
     * 
     * @param obj second object to compare
     * @return true if objects have same parameters, 
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        try {
            PatientRecord other = (PatientRecord)obj;
            if (patientId != other.patientId) {
                return false;
            }
            if (measurementValue != other.measurementValue) {
                return false;
            }
            if (!recordType.equals(other.recordType)) {
                return false;
            }
            if (timestamp != other.timestamp) {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
