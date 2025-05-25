package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.AlertStrategies.AlertStrategy;
import com.alerts.AlertStrategies.SaturationDropStrategy;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

import java.util.ArrayList;

public class SaturationDropAlertTest {

    AlertStrategy alertChecker;

    PatientRecord condition1 = new PatientRecord(1, 95, "Saturation", 1L);
    PatientRecord condition2 = new PatientRecord(1, 92, "Saturation", 2L);
    PatientRecord condition3 = new PatientRecord(1, 90, "Saturation", 3L);
    PatientRecord condition4 = new PatientRecord(1, 90, "Sys level", 3L);
    PatientRecord condition5 = new PatientRecord(1, 90, "Saturation", 1L + 10000 * 60);
    PatientRecord condition6 = new PatientRecord(1, 90, "Saturation", 1L + 10000 * 60 + 1);
    PatientRecord condition7 = new PatientRecord(1, 90.1, "Saturation", 3L);
    PatientRecord condition8 = new PatientRecord(1, 89, "Saturation", 4L);

    @BeforeEach
    void setUp() {
        alertChecker = new SaturationDropStrategy();
    }

    @Test
    void noDataTest() {
        // Edge case of no data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        assertEquals(null, alertChecker.checkCondition(records1));
    }

    @Test
    void oneDataTest() {
        // Edge case with 1 data
        ArrayList<PatientRecord> records2 = new ArrayList<PatientRecord>();
        records2.add(condition1);
        assertEquals(null, alertChecker.checkCondition(records2));
    }

    @Test
    void twoDataCase1() {
        // Case with 2 data, no alert
        ArrayList<PatientRecord> records2 = new ArrayList<PatientRecord>();
        records2.add(condition1);
        records2.add(condition2);
        assertEquals(null, alertChecker.checkCondition(records2));
    }

    @Test
    void twoDataCase2() {
        // Case with 2 data, alert
        ArrayList<PatientRecord> records3 = new ArrayList<PatientRecord>();
        records3.add(condition1);
        records3.add(condition3);
        assertEquals(new Alert("1", "Drop saturation", 3L), 
            alertChecker.checkCondition(records3));
    }

    @Test
    void labelsTest() {
        // Edge case with wrong label data, no alert
        ArrayList<PatientRecord> records4 = new ArrayList<PatientRecord>();
        records4.add(condition1);
        records4.add(condition4);
        assertEquals(null, alertChecker.checkCondition(records4));        
    }

    @Test
    void threeDataTest() {
        // Case with 3 data, drop is not immediately, alert
        ArrayList<PatientRecord> records5 = new ArrayList<PatientRecord>();
        records5.add(condition1);
        records5.add(condition2);
        records5.add(condition3);
        assertEquals(new Alert("1", "Drop saturation", 3L), 
            alertChecker.checkCondition(records5));
    }

    @Test
    void timeTest1() {
        // Edge case of 10 minutes, alert
        ArrayList<PatientRecord> records6 = new ArrayList<PatientRecord>();
        records6.add(condition1);
        records6.add(condition5);
        assertEquals(new Alert("1", "Drop saturation", 1L + 10000 * 60), 
            alertChecker.checkCondition(records6));        
    }

    @Test
    void timeTest2() {
        // Edge case of more than 10 minutes, no alert
        ArrayList<PatientRecord> records7 = new ArrayList<PatientRecord>();
        records7.add(condition1);
        records7.add(condition6);
        assertEquals(null, alertChecker.checkCondition(records7));
    }

    @Test
    void edgeCase1() {
        // Edge case of drop only by 4.9%, no alert
        ArrayList<PatientRecord> records8 = new ArrayList<PatientRecord>();
        records8.add(condition1);
        records8.add(condition7);
        assertEquals(null, alertChecker.checkCondition(records8));
    }

    @Test
    void edgeCase2() {
        //Edge case of multiple drops, find first one
        ArrayList<PatientRecord> records9 = new ArrayList<PatientRecord>();
        records9.add(condition1);
        records9.add(condition3);
        records9.add(condition8);
        assertEquals(new Alert("1", "Drop saturation", 3L), 
            alertChecker.checkCondition(records9));
    }
}
