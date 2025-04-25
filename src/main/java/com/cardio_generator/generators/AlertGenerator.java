package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {

    //No need to change to UPPER_SNAKE_CASE because not constant
    public static final Random randomGenerator = new Random();
    // Changed variable name to camelCase
    private boolean[] alertStates; // false = resolved, true = pressed

    public AlertGenerator(int patientCount) {
        // Changed variable name to camelCase
        alertStates = new boolean[patientCount + 1];
    }

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Changed variable name to camelCase
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
                    // Changed variable name to camelCase
                    alertStates[patientId] = false;
                    // Output the alert
                    // Line-wrapped because there were more than 100 characters
                    outputStrategy.output(patientId, System.currentTimeMillis(), 
                    "Alert", "resolved");
                }
            } else {
                // Changed variable name to camelCase
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                // Changed variable name to camelCase
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    // Changed variable name to camelCase
                    alertStates[patientId] = true;
                    // Output the alert
                    // Line-wrapped because there were more than 100 characters
                    outputStrategy.output(patientId, System.currentTimeMillis(), 
                    "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            // Line-wrapped because there were more than 100 characters
            System.err.println("An error occurred while generating alert data for patient " + 
            patientId);
            e.printStackTrace();
        }
    }
}
