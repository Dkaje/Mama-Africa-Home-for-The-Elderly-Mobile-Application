package com.example.mamaafricahomecare.Staff.Supervisor.Model;

public class ModelElders {
    private String elderID;
    private String name;
    private String familyMember;
    private String phoneNo;
    private String gender;
    private String dob;
    private String dateAdded;
    private String elderStatus;

    public ModelElders(String elderID, String name, String familyMember, String phoneNo,
                       String gender, String dob, String dateAdded, String elderStatus) {
        this.elderID = elderID;
        this.name = name;
        this.familyMember = familyMember;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.dob = dob;
        this.dateAdded = dateAdded;
        this.elderStatus = elderStatus;
    }

    public String getElderID() {
        return elderID;
    }

    public String getName() {
        return name;
    }

    public String getFamilyMember() {
        return familyMember;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getElderStatus() {
        return elderStatus;
    }
}
