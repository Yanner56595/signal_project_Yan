package com.alerts.Decorators;

import java.util.ArrayList;

import com.alerts.AlertStrategies.AlertStrategy;

/**
 * Decorator for prioritizing alert checkers
 * 
 * @author Yan
 */
public class PriorityAlertDecorator {

    private ArrayList<AlertStrategy> strategies;

    /**
     * Create a list which we will prioritize
     * 
     * @param strategies existing strategies to prioritize
     */
    public PriorityAlertDecorator(ArrayList<AlertStrategy> strategies) {
        this.strategies = new ArrayList<AlertStrategy>(strategies);
    }

    /**
     * Put strategy from place to first place
     * 
     * @param place current place of strategy
     */
    public void putPriority(int place) {
        if (place < strategies.size()) {
            strategies.add(0, strategies.get(place));
            strategies.remove(place + 1);
        }
    }

    public ArrayList<AlertStrategy> getStrategies() {
        return strategies;
    }
    
}
