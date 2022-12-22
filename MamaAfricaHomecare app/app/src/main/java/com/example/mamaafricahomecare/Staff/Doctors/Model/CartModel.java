package com.example.mamaafricahomecare.Staff.Doctors.Model;

public class CartModel {
    private String medID;
    private String drugName;
    private String quantity;

    public CartModel(String medID, String drugName, String quantity) {
        this.medID = medID;
        this.drugName = drugName;
        this.quantity = quantity;
    }

    public String getMedID() {
        return medID;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getQuantity() {
        return quantity;
    }

}
