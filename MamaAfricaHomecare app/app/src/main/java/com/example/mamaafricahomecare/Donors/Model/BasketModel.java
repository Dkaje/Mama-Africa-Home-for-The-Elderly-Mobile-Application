package com.example.mamaafricahomecare.Donors.Model;

public class BasketModel {
    private String itemID;
    private String itemName;
    private String quantity;

    public BasketModel(String itemID, String itemName, String quantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getQuantity() {
        return quantity;
    }
}
