package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

// Changed classname for UpperCamelCase
/**
 * Stores data about patients in a file at a specified directory.
 * 
 * @author Tom Pepels
 */
public class FileOutputStrategy implements OutputStrategy {
    
    private String baseDirectory; // Changed field name to lowerCamelCase

    // Changed field name to lowerCamelCase
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>(); 

    // Changed name of constructor to be same as class name
    /**
     * Initializes class with given path to a new directory.
     * 
     * @param baseDirectory String line which is the path for future directory.
     */
    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory; // Change field name to be same as above
    }

    /**
     * Stores the information about the patient into a file at a directory.
     * Method stores id, timespan, and data of the patient.
     * In case of errors, prints it and stops execution of method.
     * 
     * @param patientId integer value of id of a patient.
     * @param timestamp long value when measurement was taken.
     * @param label string name of file to which the data will be stored.
     * @param data string data about the patient.
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            // Changed field name to be same as above
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        // Changed field names to be same as above
        // Line-wrapped because there were more than 100 characters
        // Changed local variable to lowerCamelCase
        String filePath = fileMap.computeIfAbsent(label, k -> 
                Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        // Changed block indentation to be +2 spaces
        // Line-wrapped because there were more than 100 characters
        // Changed variable name same as above
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(
                    Paths.get(filePath), 
                    StandardOpenOption.CREATE, 
                    StandardOpenOption.APPEND))) {
            // Line-wrapped because there were more than 100 characters
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", 
                    patientId, timestamp, label, data);
        } catch (Exception e) {
            // Changed variable name same as above
            System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
        }
    }
}