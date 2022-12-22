package com.example.mamaafricahomecare.Donors.Model;

public class ItemModel {
    private String itemName;
    private String quantity;

    public ItemModel(String itemName, String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getQuantity() {
        return quantity;
    }
}
