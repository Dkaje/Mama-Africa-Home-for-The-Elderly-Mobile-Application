package com.example.mamaafricahomecare.Model;

public class IssuedDrugsModel {
    private String drugName;
    private String quantity;

    public IssuedDrugsModel(String drugName, String quantity) {

        this.drugName = drugName;
        this.quantity = quantity;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getQuantity() {
        return quantity;
    }

}
