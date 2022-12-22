package com.example.mamaafricahomecare.Supplier.Model;

public class MySuppliesModel {

    private String requestID;
    private String requestDate;
    private String requestStatus;

    public MySuppliesModel(String requestID, String requestDate, String requestStatus) {
        this.requestID = requestID;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

}
