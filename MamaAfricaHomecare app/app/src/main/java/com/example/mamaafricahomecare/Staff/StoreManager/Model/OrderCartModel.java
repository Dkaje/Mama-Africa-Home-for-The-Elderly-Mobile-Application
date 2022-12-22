package com.example.mamaafricahomecare.Staff.StoreManager.Model;

public class OrderCartModel {

    private String id;
    private String drugName;
    private String quantity;

    public OrderCartModel(String id, String drugName, String quantity) {
        this.id = id;
        this.drugName = drugName;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getQuantity() {
        return quantity;
    }
}
