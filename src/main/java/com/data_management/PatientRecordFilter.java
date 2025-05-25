package com.data_management;

import java.util.ArrayList;

/**
 * Class for filtering Patients data by parameters
 * 
 * @author Yan
 */
public class PatientRecordFilter {
    
    /**
     * Extract from overall data only data of specific type
     * 
     * @param records arrayList with all Patient data
     * @param recordType String name of type, which we're looking for
     * @return ArrayList of data of specific type of measurement
     */
    public ArrayList<PatientRecord> getFilteredRecords(ArrayList<PatientRecord> records, String recordType) {
        ArrayList<PatientRecord> sortedRecords = new ArrayList<PatientRecord>();
        for (int i = 0; i < records.size(); i++) {
            PatientRecord record = records.get(i);
            if (record.getRecordType().equals(recordType)) {
                sortedRecords.add(record);
            }
        }
        return sortedRecords;
    }
}
