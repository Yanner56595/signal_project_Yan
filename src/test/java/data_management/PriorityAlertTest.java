package data_management;

import org.junit.jupiter.api.Test;

import com.alerts.Decorators.PriorityAlertDecorator;
import com.alerts.AlertStrategies.AlertStrategy;
import com.alerts.AlertStrategies.BloodPressureThresholdStrategy;
import com.alerts.AlertStrategies.BloodPressureTrendStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

public class PriorityAlertTest {
    
    @Test
    void test1() {
        // Test switching 2 strategies
        ArrayList<AlertStrategy> strategies = new ArrayList<AlertStrategy>();
        AlertStrategy strategy1 = new BloodPressureThresholdStrategy();
        AlertStrategy strategy2 = new BloodPressureTrendStrategy();
        strategies.add(strategy1);
        strategies.add(strategy2);
        PriorityAlertDecorator decorator = new PriorityAlertDecorator(strategies);
        decorator.putPriority(1);
        ArrayList<AlertStrategy> strategiesModified = decorator.getStrategies();
        assertNotEquals(strategies, strategiesModified);
        assertEquals(strategiesModified.get(0), strategy2);
        assertEquals(strategiesModified.get(1), strategy1);
    }

    @Test
    void test2() {
        // Test switching 1 strategy on its own place
        ArrayList<AlertStrategy> strategies = new ArrayList<AlertStrategy>();
        AlertStrategy strategy1 = new BloodPressureThresholdStrategy();
        strategies.add(strategy1);
        PriorityAlertDecorator decorator = new PriorityAlertDecorator(strategies);
        decorator.putPriority(0);
        ArrayList<AlertStrategy> strategiesModified = decorator.getStrategies();
        assertEquals(strategiesModified.get(0), strategy1);
    }

    @Test
    void test3() {
        // Edge case wrong place
        ArrayList<AlertStrategy> strategies = new ArrayList<AlertStrategy>();
        AlertStrategy strategy1 = new BloodPressureThresholdStrategy();
        strategies.add(strategy1);
        PriorityAlertDecorator decorator = new PriorityAlertDecorator(strategies);
        decorator.putPriority(1);
        ArrayList<AlertStrategy> strategiesModified = decorator.getStrategies();
        assertEquals(strategiesModified.get(0), strategy1);
    }
}
