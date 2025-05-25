package data_management;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.alerts.AlertTypes.AlertFactory;

public class AlertFactoryTest {
    
    @Test
    void testLength() {
        AlertFactory factory = new AlertFactory();
        assertEquals(6, factory.loadAllAlerts().size());
    }
}
