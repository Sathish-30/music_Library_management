package com.dexter.model;

public class Publisher {
    private int publisherId;
    private String publisherName;
    private String emailId;
    private String password;

    public Publisher(String publisherName, String emailId, String password) {
        this.publisherName = publisherName;
        this.emailId = emailId;
        this.password = password;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
