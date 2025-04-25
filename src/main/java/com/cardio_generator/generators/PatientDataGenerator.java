package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Ensures that classes implement generate method for a given patient and output source.
 * 
 * @author Tom Pepels
 */
public interface PatientDataGenerator {

    /**
     * Generates some data for the patient and outputs it to the source.
     * 
     * @param patientId integer value of the id of a patient.
     * @param outputStrategy value of OutputStrategy class which specifies output source.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
