package com.example.mamaafricahomecare.Staff.CareGiver.Models;

public class FeedModel {
    private String memberID;
    private String name;

    public FeedModel(String memberID, String name) {
        this.memberID = memberID;
        this.name = name;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }
}
