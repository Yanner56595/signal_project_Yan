package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.AlertTypes.AlertCondition;
import com.alerts.AlertTypes.BloodPressureTrendAlert;
import com.data_management.PatientRecord;

import java.util.ArrayList;

public class BloodPressureTrendAlertTest {

    AlertCondition alertChecker;

    PatientRecord condition1 = new PatientRecord(1, 100, "SystolicPressure", 1L);
    PatientRecord condition2 = new PatientRecord(1, 110, "SystolicPressure", 2L);
    PatientRecord condition3 = new PatientRecord(1, 120, "SystolicPressure", 3L);
    PatientRecord condition4 = new PatientRecord(1, 111, "SystolicPressure", 4L);
    PatientRecord condition5 = new PatientRecord(1, 122, "SystolicPressure", 5L);
    PatientRecord condition6 = new PatientRecord(1, 120, "SystolicPressure", 6L);
    PatientRecord condition7 = new PatientRecord(1, 100, "DiastolicPressure", 1L);
    PatientRecord condition8 = new PatientRecord(1, 110, "DiastolicPressure", 2L);
    PatientRecord condition9 = new PatientRecord(1, 120, "DiastolicPressure", 3L);
    PatientRecord condition10 = new PatientRecord(1, 111, "DiastolicPressure", 4L);
    PatientRecord condition11 = new PatientRecord(1, 122, "DiastolicPressure", 5L);
    PatientRecord condition12 = new PatientRecord(1, 120, "DiastolicPressure", 6L);

    @BeforeEach
    void setUp() {
        alertChecker = new BloodPressureTrendAlert();
    }

    @Test
    void noDataTest() {
        // Edge case no data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        assertEquals(null, alertChecker.evaluate("1", records1));
    }

    @Test
    void oneDataTest() {
        // Edge case 1 data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        assertEquals(null, alertChecker.evaluate("1", records1));
    }

    @Test
    void twoDataTest() {
        // Edge case 2 data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        records1.add(condition2);
        assertEquals(null, alertChecker.evaluate("1", records1));
    }

    @Test
    void threeDataTest() {
        // Case 3 data, but no alert
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        records1.add(condition2);
        records1.add(condition3);
        records1.add(condition7);
        records1.add(condition8);
        records1.add(condition9);
        assertEquals(null, alertChecker.evaluate("1", records1));
    }

    @Test
    void increasingTest() {
        // Case 3 data, increasing systolic alert
        ArrayList<PatientRecord> records2 = new ArrayList<PatientRecord>();
        records2.add(condition1); 
        records2.add(condition4); 
        records2.add(condition5);
        assertEquals(new Alert(
            "1", 
            "Increasing systolic blood pressure trend", 
            5L), 
            alertChecker.evaluate("1", records2));

        // Case 3 data, increasing diastolic alert
        records2 = new ArrayList<PatientRecord>();
        records2.add(condition7); 
        records2.add(condition10); 
        records2.add(condition11);
        assertEquals(new Alert(
            "1", 
            "Increasing diastolic blood pressure trend", 
            5L), 
            alertChecker.evaluate("1", records2));
    }

    @Test
    void decreasingTest() {
        // Case 3 data, decreasing systolic alert
        ArrayList<PatientRecord> records3 = new ArrayList<PatientRecord>();
        records3.add(condition5); 
        records3.add(condition4); 
        records3.add(condition1);
        assertEquals(new Alert(
            "1", 
            "Decreasing systolic blood pressure trend", 
            1L), 
            alertChecker.evaluate("1", records3));

        // Case 3 data, decreasing diastolic alert
        records3 = new ArrayList<PatientRecord>();
        records3.add(condition11); 
        records3.add(condition10); 
        records3.add(condition7);
        assertEquals(new Alert(
            "1", 
            "Decreasing diastolic blood pressure trend", 
            1L), 
            alertChecker.evaluate("1", records3));
    }

    @Test
    void fluctuateTest() {
        // Fluctuating case, no alert
        ArrayList<PatientRecord> records4 = new ArrayList<PatientRecord>();
        records4.add(condition1);
        records4.add(condition5);
        records4.add(condition6);
        records4.add(condition7);
        records4.add(condition11);
        records4.add(condition12);
        assertEquals(null, alertChecker.evaluate("1", records4));
    }

    @Test
    void mixedTest() {
        // Mixed types, no alert
        ArrayList<PatientRecord> records5 = new ArrayList<PatientRecord>();
        records5.add(condition1);
        records5.add(condition10);
        records5.add(condition5);
        assertEquals(null, alertChecker.evaluate("1", records5));
    }
    
}
