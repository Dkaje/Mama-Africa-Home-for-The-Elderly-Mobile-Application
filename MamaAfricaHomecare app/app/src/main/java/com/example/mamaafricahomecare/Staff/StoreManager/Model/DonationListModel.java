package com.example.mamaafricahomecare.Staff.StoreManager.Model;

public class DonationListModel {
    private String donationID;
    private String name;
    private String danationDate;
    private String donationStatus;
    private String donationRemarks;

    public DonationListModel(String donationID, String name, String danationDate,
                             String donationStatus, String donationRemarks) {
        this.donationID = donationID;
        this.name = name;
        this.danationDate = danationDate;
        this.donationStatus = donationStatus;
        this.donationRemarks = donationRemarks;
    }

    public String getDonationID() {
        return donationID;
    }

    public String getName() {
        return name;
    }

    public String getDanationDate() {
        return danationDate;
    }

    public String getDonationStatus() {
        return donationStatus;
    }

    public String getDonationRemarks() {
        return donationRemarks;
    }

}
