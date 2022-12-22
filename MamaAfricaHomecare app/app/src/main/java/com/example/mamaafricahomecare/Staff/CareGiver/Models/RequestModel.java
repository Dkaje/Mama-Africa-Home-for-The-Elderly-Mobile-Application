package com.example.mamaafricahomecare.Staff.CareGiver.Models;

public class RequestModel {
    private  String requestID;
    private  String name;
    private  String gender;
    private  String requestDate;
    private  String requestStatus;

    public RequestModel(String requestID, String name, String gender, String requestDate, String requestStatus) {
        this.requestID = requestID;
        this.name = name;
        this.gender = gender;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }
}
