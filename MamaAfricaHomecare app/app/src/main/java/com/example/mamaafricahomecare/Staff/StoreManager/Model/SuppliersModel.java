package com.example.mamaafricahomecare.Staff.StoreManager.Model;

public class SuppliersModel {

    private String supplierID;
    private String name;
    private String phoneNo;

    public SuppliersModel(String supplierID, String name, String phoneNo) {
        this.supplierID = supplierID;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
