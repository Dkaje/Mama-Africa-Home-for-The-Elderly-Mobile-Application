package com.example.mamaafricahomecare.Model;

public class ItemDetailModel {
    private String itemName;
    private String quantity;
    private String reqestDetail;

    public ItemDetailModel(String itemName, String quantity, String reqestDetail) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.reqestDetail = reqestDetail;
    }

    public String getItemName() {
        return itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getReqestDetail() {
        return reqestDetail;
    }
}
