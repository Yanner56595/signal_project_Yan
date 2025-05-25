package com.alerts.AlertTypes;

// Represents an alert
public class Alert {
    private String patientId;
    private String condition;
    private long timestamp;

    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getCondition() {
        return condition;
    }

    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Checks for equality of alerts
     * 
     * @param obj second object to compare
     * @return true if objects have same parameters, 
     *         false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        try {
            Alert other = (Alert)obj;
            if (!this.patientId.equals(other.getPatientId())) {
                return false;
            }
            if (!this.condition.equals(other.getCondition())) {
                return false;
            }
            if (this.timestamp != other.getTimestamp()) {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
