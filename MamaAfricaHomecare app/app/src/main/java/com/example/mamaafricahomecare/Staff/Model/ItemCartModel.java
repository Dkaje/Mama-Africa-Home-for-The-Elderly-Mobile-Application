package com.example.mamaafricahomecare.Staff.Model;

public class ItemCartModel {
    private String itemID;
    private String itemName;
    private String quantity;
    private String itemDetail;

    public ItemCartModel(String itemID,String itemName, String quantity, String itemDetail) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemDetail = itemDetail;
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

    public String getItemDetail() {
        return itemDetail;
    }
}
