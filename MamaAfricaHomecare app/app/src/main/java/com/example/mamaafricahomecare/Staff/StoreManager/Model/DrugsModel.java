package com.example.mamaafricahomecare.Staff.StoreManager.Model;

public class DrugsModel {

    private String drugName;
    private String stock;

    public DrugsModel(String drugName, String stock) {
        this.drugName = drugName;
        this.stock = stock;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getStock() {
        return stock;
    }
}
