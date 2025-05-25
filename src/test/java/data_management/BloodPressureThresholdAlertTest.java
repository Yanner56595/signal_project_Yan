package data_management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.AlertTypes.Alert;
import com.alerts.AlertStrategies.AlertStrategy;
import com.alerts.AlertStrategies.BloodPressureThresholdStrategy;
import com.data_management.PatientRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class BloodPressureThresholdAlertTest {

    AlertStrategy alertChecker;

    PatientRecord condition1 = new PatientRecord(1, 180, "SystolicPressure", 1L);
    PatientRecord condition2 = new PatientRecord(1, 160, "SystolicPressure", 2L);
    PatientRecord condition3 = new PatientRecord(1, 181, "SystolicPressure", 3L);
    PatientRecord condition4 = new PatientRecord(1, 90, "SystolicPressure", 4L);
    PatientRecord condition5 = new PatientRecord(1, 89, "SystolicPressure", 5L);
    PatientRecord condition6 = new PatientRecord(1, 120, "DiastolicPressure", 1L);
    PatientRecord condition7 = new PatientRecord(1, 121, "DiastolicPressure", 2L);
    PatientRecord condition8 = new PatientRecord(1, 100, "DiastolicPressure", 3L);
    PatientRecord condition9 = new PatientRecord(1, 60, "DiastolicPressure", 4L);
    PatientRecord condition10 = new PatientRecord(1, 59, "DiastolicPressure", 5L);

    @BeforeEach
    void setUp() {
        alertChecker = new BloodPressureThresholdStrategy();
    }

    @Test
    void noDataTest() {
        // Edge case no data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        assertEquals(null, alertChecker.checkCondition(records1));        
    }

    @Test
    void test1() {
        //Edge cases, no alert
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        records1.add(condition2);
        records1.add(condition4);
        records1.add(condition6);
        records1.add(condition8);
        records1.add(condition9);
        assertEquals(null, alertChecker.checkCondition(records1)); 
    }

    @Test
    void upperTest() {
        //Check upper limits
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition1);
        records1.add(condition3);
        assertEquals(new Alert(
            "1", 
            "Blood Threshold Alert", 
            3L), 
            alertChecker.checkCondition(records1));

        records1 = new ArrayList<PatientRecord>();
        records1.add(condition6);
        records1.add(condition7);
        assertEquals(new Alert(
            "1", 
            "Blood Threshold Alert", 
            2L), 
            alertChecker.checkCondition(records1));
    }

    @Test
    void lowerTest() {
        //Check lower limits
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        records1.add(condition4);
        records1.add(condition5);
        assertEquals(new Alert(
            "1", 
            "Blood Threshold Alert", 
            5L), 
            alertChecker.checkCondition(records1));

        records1 = new ArrayList<PatientRecord>();
        records1.add(condition9);
        records1.add(condition10);
        assertEquals(new Alert(
            "1", 
            "Blood Threshold Alert", 
            5L), 
            alertChecker.checkCondition(records1));        
    } 
}
