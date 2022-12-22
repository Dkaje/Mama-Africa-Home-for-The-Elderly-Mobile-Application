package com.example.mamaafricahomecare.Staff.CareGiver.Models;

public class ModelDoctors {
    private String staffID;
    private String name;
    private String role;
    private String phoneNo;

    public ModelDoctors(String staffID, String name,String role, String phoneNo) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.phoneNo = phoneNo;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
