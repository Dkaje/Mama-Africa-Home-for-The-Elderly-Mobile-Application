package com.example.mamaafricahomecare.Donors.Model;

public class DonationModel {
    private String donationID;
    private String donationStatus;
    private String donationDate;
    private String donationRemarks;

    public DonationModel(String donationID, String donationStatus,
                         String donationDate, String donationRemarks) {
        this.donationID = donationID;
        this.donationStatus = donationStatus;
        this.donationDate = donationDate;
        this.donationRemarks = donationRemarks;
    }

    public String getDonationID() {
        return donationID;
    }

    public String getDonationStatus() {
        return donationStatus;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public String getDonationRemarks() {
        return donationRemarks;
    }
}
