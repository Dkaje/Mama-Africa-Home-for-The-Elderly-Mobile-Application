package com.example.mamaafricahomecare.Staff.Doctors.Model;

public class LabTestModel {
    private  String appointmentID;
    private  String name;
    private  String familyMember;
    private  String gender;
    private  String dob;
    private  String appointmentDate;
    private  String testStatus;
    private  String appointmentDetails;
    private  String remarks;
    private  String testResults;
    private  String careGiver;

    public LabTestModel(String appointmentID, String name, String familyMember, String gender,
                        String dob, String appointmentDate,
                        String testStatus, String appointmentDetails, String remarks,
                        String testResults, String careGiver) {
        this.appointmentID = appointmentID;
        this.name = name;
        this.familyMember = familyMember;
        this.gender = gender;
        this.dob = dob;
        this.appointmentDate = appointmentDate;
        this.testStatus = testStatus;
        this.appointmentDetails = appointmentDetails;
        this.remarks = remarks;
        this.testResults = testResults;
        this.careGiver = careGiver;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public String getAppointmentDetails() {
        return appointmentDetails;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getTestResults() {
        return testResults;
    }

    public String getCareGiver() {
        return careGiver;
    }
}
