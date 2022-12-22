package com.example.mamaafricahomecare.Staff.Model;

public class AppointmentsModel {
    private  String appointmentID;
    private  String name;
    private  String familyMember;
    private  String gender;
    private  String dob;
    private  String appointmentDate;
    private  String appointmentStatus;
    private  String appointmentDetails;
    private  String remarks;

    public AppointmentsModel(String appointmentID, String name,String familyMember, String gender, String dob,
                             String appointmentDate, String appointmentStatus,
                             String appointmentDetails, String remarks) {
        this.appointmentID = appointmentID;
        this.name = name;
        this.gender = gender;
        this.familyMember = familyMember;
        this.dob = dob;
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
        this.appointmentDetails = appointmentDetails;
        this.remarks = remarks;
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

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public String getAppointmentDetails() {
        return appointmentDetails;
    }

    public String getRemarks() {
        return remarks;
    }
}
