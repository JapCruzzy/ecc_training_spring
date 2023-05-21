package com.example.ecctrainingspring.enums;

public enum EnumDepartment {
    IT("Information Technology", 0),
    ADMIN("Administrator", 1),
    HR("Human resources", 2),
    SALES("Sales and Marketing", 3);

    EnumDepartment(String deptName, int deptId) {
        this.deptName = deptName;
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public int getDeptId() {
        return deptId;
    }

    private String deptName;
    private int deptId;

    public static EnumDepartment getById(int deptId) {
        for(EnumDepartment e : values()) {
            if(e.deptId == deptId) return e;
        }
        return null;
    }
}
