package com.example.mamaafricahomecare.Staff.StoreManager.Model;

public class OrdersRequestsModel {
    private String requestID;
    private String name;
    private String requestDate;
    private String requestStatus;

    public OrdersRequestsModel(String requestID, String name,
                               String requestDate, String requestStatus) {
        this.requestID = requestID;
        this.name = name;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getName() {
        return name;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }
}
