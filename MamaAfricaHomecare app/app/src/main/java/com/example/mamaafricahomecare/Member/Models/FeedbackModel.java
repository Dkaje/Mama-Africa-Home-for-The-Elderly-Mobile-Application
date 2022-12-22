package com.example.mamaafricahomecare.Member.Models;

public class FeedbackModel {
    String comment;
    String reply;

    public FeedbackModel(String comment, String reply){
        this.comment=comment;
        this.reply=reply;
    }

    public String getComment() {
        return comment;
    }

    public String getReply() {
        return reply;
    }
}
