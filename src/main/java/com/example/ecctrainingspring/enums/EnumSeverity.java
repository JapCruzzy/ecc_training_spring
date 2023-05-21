package com.example.ecctrainingspring.enums;

public enum EnumSeverity {
    LOW(0, "Low priority"),
    NORMAL(1, "Normal priority"),
    MAJOR(2, "Major priority"),
    CRITICAL(3, "Critical priority");

    private int severityId;

    private String severityType;

    EnumSeverity(int severityId, String severityType) {
        this.severityId = severityId;
        this.severityType = severityType;
    }

    public int getSeverityId() {
        return severityId;
    }

    public String getSeverityType() {
        return severityType;
    }


    public static EnumSeverity getById(int severityId) {
        for(EnumSeverity e : values()) {
            if(e.severityId == severityId) return e;
        }
        return null;
    }
}
