package data_management;

import org.junit.jupiter.api.Test;

import com.cardio_generator.HealthDataSimulator;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class HealthDataSimulatorTest {

    @Test
    void test1() {
        // Test singleton
        HealthDataSimulator test = HealthDataSimulator.getInstance();
        assertEquals(test, HealthDataSimulator.getInstance());
    }
    
}
