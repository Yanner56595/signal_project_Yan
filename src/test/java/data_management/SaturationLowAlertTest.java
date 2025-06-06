package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.AlertStrategies.AlertStrategy;
import com.alerts.AlertStrategies.SaturationLowStrategy;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

import java.util.ArrayList;

public class SaturationLowAlertTest {

    AlertStrategy alertChecker;

    PatientRecord condition1 = new PatientRecord(1, 95, "Saturation", 1L);
    PatientRecord condition2 = new PatientRecord(1, 92, "Saturation", 2L);
    PatientRecord condition3 = new PatientRecord(1, 90, "Saturation", 3L);
    PatientRecord condition4 = new PatientRecord(1, 90, "Sys level", 3L);

    @BeforeEach
    void setUp() {
        alertChecker = new SaturationLowStrategy();
    }

    @Test
    void noDataTest() {
        //Edge case of no data
        ArrayList<PatientRecord> records1 = new ArrayList<PatientRecord>();
        assertEquals(null, alertChecker.checkCondition(records1));
    }

    @Test
    void noAlertTest() {
        //Check for no alert case
        ArrayList<PatientRecord> records2 = new ArrayList<PatientRecord>();
        records2.add(condition1);
        assertEquals(null, alertChecker.checkCondition(records2));
    }

    @Test
    void noAlertTest2() {
        //Edge case for no alert case
        ArrayList<PatientRecord> records3 = new ArrayList<PatientRecord>();
        records3.add(condition1);
        records3.add(condition2);
        assertEquals(null, alertChecker.checkCondition(records3));
    }

    @Test
    void alertTest() {
        //Alert case
        ArrayList<PatientRecord> records4 = new ArrayList<PatientRecord>();
        records4.add(condition1);
        records4.add(condition3);
        assertEquals(new Alert("1", "Low saturation", 3L), 
                alertChecker.checkCondition(records4));
    }

    @Test
    void valuesTest() {
        //Edge case with alert value but wrong type of measurement
        ArrayList<PatientRecord> records5 = new ArrayList<PatientRecord>();
        records5.add(condition1);
        records5.add(condition4);
        assertEquals(null, alertChecker.checkCondition(records5));
    }
    
}
