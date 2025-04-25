package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;

// Changed classname for UpperCamelCase
public class FileOutputStrategy implements OutputStrategy {
    
    private String baseDirectory; // Changed field name to lowerCamelCase

    // Changed field name to lowerCamelCase
    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>(); 

    // Changed name of constructor to be same as class name
    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory; // Change field name to be same as above
    }

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