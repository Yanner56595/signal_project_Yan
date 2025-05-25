package data_management;

import org.junit.jupiter.api.Test;

import com.alerts.Alert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class AlertTest {
    
    @Test
    void testEquality0() {
        // Check if same
        Alert alert1 = new Alert("1", "Type 1", 1L);
        assertEquals(alert1, alert1);
    }

    @Test
    void testEquality1() {
        Alert alert1 = new Alert("1", "Type 1", 1L);
        Alert alert2 = new Alert("1", "Type 1", 1L);
        assertEquals(alert1, alert2);
    }

    @Test
    void testEquality2() {
        //Check patient Id comparison
        Alert alert1 = new Alert("1", "Type 1", 1L);
        Alert alert2 = new Alert("2", "Type 1", 1L);
        assertNotEquals(alert1, alert2);
    }

    @Test
    void testEquality3() {
        //Check condition comparison
        Alert alert1 = new Alert("1", "Type 2", 1L);
        Alert alert2 = new Alert("1", "Type 1", 1L);
        assertNotEquals(alert1, alert2);
    }

    @Test
    void testEquality4() {
        //Check timestamp comparison
        Alert alert1 = new Alert("1", "Type 1", 2L);
        Alert alert2 = new Alert("1", "Type 1", 1L);
        assertNotEquals(alert1, alert2);
    }

    @Test
    void testEquality5() {
        //Check with null
        Alert alert1 = new Alert("1", "Type 1", 2L);
        assertNotEquals(alert1, null);
    }

    @Test
    void testEquality6() {
        //Check with null
        Alert alert1 = new Alert("1", "Type 1", 2L);
        Object alert2 = "Hello World!"; 
        assertNotEquals(alert1, alert2);
    }
}
