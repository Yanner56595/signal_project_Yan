package com.data_management;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Parses data from an output file and passes it to DataStorage.
 * 
 * @author Yan
 */
public class DataParser implements DataReader {

    private String filePath;

    /**
     * Initializes class with specified path to a file.
     * 
     * @param filePath string path to a file.
     */
    public DataParser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from file and parse information to data storage.
     * 
     * @param dataStorage the storage of data to which we parse data.
     * @throws IOException if the specified file wasn't found.
     */
    @Override
    public void readData(DataStorage dataStorage) throws IOException {
      File myFile = new File(filePath);
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] values = parseString(data);
        try {
            int patientId = Integer.parseInt(values[0]);
            long timestamp = Long.parseLong(values[1]);
            String label = values[2];
            double measurementValue = Double.parseDouble(values[3]);
            dataStorage.addPatientData(patientId, measurementValue, label, timestamp);
        }
        catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }
      }
      myReader.close(); 
    }

    /**
     * Parses string to get actual values of data of a patient.
     * 
     * @param data string line with data about patien in format of FileOutputStrategy.
     * @return array of string values of data.
     */
    public String[] parseString(String data) {
        String[] parameters = data.split(", ");
        String[] parsedData = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parsedData[i] = parameters[i].split(": ")[1].split("%")[0];
        }
        return parsedData;
    }

}
