package com.cardio_generator.outputs;

/**
 * Ensures that classes implement output of info of a patient.
 * 
 * @author Tom Pepels
 */
public interface OutputStrategy {

    /**
     * Outputs the data about the patient.
     * Method sends id, timespan, label, and data of the patient.
     * 
     * @param patientId integer value of id of a patient.
     * @param timestamp long value when measurement was taken.
     * @param label string label of the data.
     * @param data string data about the patient.
     */
    void output(int patientId, long timestamp, String label, String data);
}