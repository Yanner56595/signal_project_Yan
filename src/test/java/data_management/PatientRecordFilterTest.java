package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.data_management.PatientRecord;
import com.data_management.PatientRecordFilter;

import java.util.ArrayList;

public class PatientRecordFilterTest {

    ArrayList<PatientRecord> records;
    PatientRecordFilter filter;

    PatientRecord record1 = new PatientRecord(1, 1, "Type 1", 15);
    PatientRecord record2 = new PatientRecord(1, 2, "Type 1", 15);
    PatientRecord record3 = new PatientRecord(1, 2, "Type 2", 15);

    @BeforeEach
    void setUp() {
        records = new ArrayList<PatientRecord>();
        filter = new PatientRecordFilter();

        records.add(record1);
        records.add(record2);
        records.add(record3);
    }

    @Test
    void testFilter1() {
        ArrayList<PatientRecord> type1 = new ArrayList<PatientRecord>();
        type1.add(record1);
        type1.add(record2);

        ArrayList<PatientRecord> type1Res = filter.getFilteredRecords(records, "Type 1");
        assertEquals(2, type1Res.size()); // Check if two records are retrieved
        //Check content
        for (int i = 0; i < type1.size(); i++) {
            assertEquals(type1.get(i), type1Res.get(i));
        }
    }

    @Test
    void testFilter2() {
        ArrayList<PatientRecord> type2 = new ArrayList<PatientRecord>();
        type2.add(record3);

        ArrayList<PatientRecord> type2Res = filter.getFilteredRecords(records, "Type 2");
        assertEquals(1, type2Res.size()); // Check if one records are retrieved
        //Check content
        for (int i = 0; i < type2.size(); i++) {
            assertEquals(type2.get(i), type2Res.get(i));
        }
    }

    @Test
    void testFilter3()
    {
        // Check if none records are retrieved
        assertEquals(0, 
            filter.getFilteredRecords(records, "Type 3").size()); 
    }

    @Test
    void testEdgeCasesFilter() {
        //Edge cases
        assertEquals(0, 
            filter.getFilteredRecords(
                records, null).size()); // Check if works properly with null

        assertEquals(0, 
            filter.getFilteredRecords(
                new ArrayList<PatientRecord>(), null).size()); // Check if works properly

        assertEquals(0, 
            filter.getFilteredRecords(
                new ArrayList<PatientRecord>(), "Type 1").size()); // Check if works properly
    }

}
