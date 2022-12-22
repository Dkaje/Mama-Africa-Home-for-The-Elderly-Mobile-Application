package com.example.mamaafricahomecare.Staff.Pharmacist.Model;

public class DrugIssueModel {
    private  String appointmentID;
    private  String name;
    private  String familyMember;
    private  String gender;
    private  String dob;
    private  String dStatus;
    private  String appointmentDetails;

    public DrugIssueModel(String appointmentID, String name, String familyMember,
                          String gender, String dob, String dStatus, String appointmentDetails) {
        this.appointmentID = appointmentID;
        this.name = name;
        this.familyMember = familyMember;
        this.gender = gender;
        this.dob = dob;
        this.dStatus = dStatus;
        this.appointmentDetails = appointmentDetails;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public String getName() {
        return name;
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getdStatus() {
        return dStatus;
    }

    public String getAppointmentDetails() {
        return appointmentDetails;
    }
}
