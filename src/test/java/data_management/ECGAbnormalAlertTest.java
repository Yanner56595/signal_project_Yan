package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.AlertStrategies.AlertStrategy;
import com.alerts.AlertStrategies.ECGAbnormalStrategy;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

import java.util.ArrayList;

public class ECGAbnormalAlertTest {
    AlertStrategy alertChecker;

    PatientRecord condition1 = new PatientRecord(1, 10, "ECG", 1L);
    PatientRecord condition2 = new PatientRecord(1, 15, "ECG", 2L);
    PatientRecord condition3 = new PatientRecord(1, 25, "ECG", 3L);
    PatientRecord condition4 = new PatientRecord(1, 24.9, "ECG", 4L);
    PatientRecord condition5 = new PatientRecord(1, 95, "Saturation", 4L);
    PatientRecord condition6 = new PatientRecord(1, 52, "ECG", 4L);
    PatientRecord condition7 = new PatientRecord(1, 60, "ECG", 5L);

    @BeforeEach
    void setUp() {
        alertChecker = new ECGAbnormalStrategy();
    }

    @Test
    void noDataTest() {
        // Edge case with no data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        assertEquals(null, alertChecker.checkCondition(records1));
    }

    @Test
    void oneDataTest() {
        // Edge case with 1 data, no alert
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        assertEquals(null, alertChecker.checkCondition(records1));
    }

    @Test
    void sameDataTest() {
        // Edge case with all same data, no alert
        ArrayList<PatientRecord> records2 = new ArrayList<PatientRecord>();
        records2.add(condition1);
        records2.add(condition1);
        records2.add(condition1);
        assertEquals(null, alertChecker.checkCondition(records2));
    }

    @Test
    void peakTest1() {
        // Edge case with peak at exactly 1.5 times
        ArrayList<PatientRecord> records3 = new ArrayList<PatientRecord>();
        records3.add(condition1);
        records3.add(condition2);
        records3.add(condition3);
        assertEquals(new Alert("1", "ECG Alert", 3L), 
                alertChecker.checkCondition(records3));
    }

    @Test
    void peakTest2() {
        // Edge case with insufficient peak 
        ArrayList<PatientRecord> records4 = new ArrayList<PatientRecord>();
        records4.add(condition1);
        records4.add(condition2);
        records4.add(condition4);
        assertEquals(null, alertChecker.checkCondition(records4));
    }

    @Test
    void dataTypeTest() {
        // Edge case with peak at wrong data type, no alert 
        ArrayList<PatientRecord> records5 = new ArrayList<PatientRecord>();
        records5.add(condition1);
        records5.add(condition2);
        records5.add(condition5);
        assertEquals(null, alertChecker.checkCondition(records5));
    }

    @Test
    void multiplePeaksTest() {
        // Edge case with multiple peaks, find first one
        ArrayList<PatientRecord> records6 = new ArrayList<PatientRecord>();
        records6.add(condition1);
        records6.add(condition2);
        records6.add(condition6);
        records6.add(condition7);
        assertEquals(new Alert("1", "ECG Alert", 4L), 
                alertChecker.checkCondition(records6));
    }
}
