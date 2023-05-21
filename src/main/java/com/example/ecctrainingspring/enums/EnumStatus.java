package com.example.ecctrainingspring.enums;


public enum EnumStatus {
    NEW(0, "New"),
    ASSIGNED(1, "Already Assigned"),
    IN_PROGRESS(2, "In Progress"),
    CLOSED(3, "Closed");

    private int statusId;

    private String statusType;

    EnumStatus(int statusId, String statusType) {
        this.statusId = statusId;
        this.statusType = statusType;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getStatusType() {
        return statusType;
    }

    public static EnumStatus getById(int statusId) {
        for(EnumStatus e : values()) {
            if(e.statusId == statusId) return e;
        }
        return null;
    }
}
