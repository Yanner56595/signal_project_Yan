package data_management;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.alerts.Alert;
import com.alerts.AlertTypes.AlertCondition;
import com.alerts.AlertTypes.TriggeredButtonAlert;

public class TriggeredButtonAlertTest {

    @Test
    void testTriggeredAlert() {
        AlertCondition triggeredAlert = new TriggeredButtonAlert();

        assertEquals(new Alert(
            "1", "Manual Alert", System.currentTimeMillis()), 
            triggeredAlert.evaluate("1", null));
    }
    
}
