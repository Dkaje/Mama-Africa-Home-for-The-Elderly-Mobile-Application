package com.example.mamaafricahomecare.Staff.Supervisor.Model;

public class ModelCareGiver {
    private String staffID;
    private String name;
    private String phoneNo;

    public ModelCareGiver(String staffID, String name, String phoneNo) {
        this.staffID = staffID;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
