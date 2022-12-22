package com.example.mamaafricahomecare.Member.Models;

public class ElderModel {
    private String  elderID;
    private String  name;
    private String gender ;
    private String dob ;
    private String  dateAdded;
    private String  elderStatus;

    public ElderModel(String elderID, String name, String gender, String dob,
                      String dateAdded, String elderStatus) {
        this.elderID = elderID;
        this.name = name;
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
