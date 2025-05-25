package data_management;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.alerts.AlertStrategies.AlertStrategy;
import com.alerts.AlertStrategies.TriggeredButtonStrategy;
import com.alerts.AlertTypes.Alert;
import com.data_management.PatientRecord;

public class TriggeredButtonAlertTest {

    @Test
    void testTriggeredAlert() {
        AlertStrategy triggeredAlert = new TriggeredButtonStrategy();

        assertEquals(new Alert(
            "-1", "Attention: Manual Alert", System.currentTimeMillis()), 
            triggeredAlert.checkCondition(new ArrayList<PatientRecord>()));
    }
    
}
