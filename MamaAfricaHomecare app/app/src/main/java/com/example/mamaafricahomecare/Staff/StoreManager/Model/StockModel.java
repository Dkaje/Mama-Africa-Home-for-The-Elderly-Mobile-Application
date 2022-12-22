package com.example.mamaafricahomecare.Staff.StoreManager.Model;

public class StockModel {
    private String itemID;
    private String itemName;
    private String stock;

    public StockModel(String itemID, String itemName, String stock) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.stock = stock;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getStock() {
        return stock;
    }
}
