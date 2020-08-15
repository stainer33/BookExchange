package com.example.bookexchange1.Model;

public class Notification {
    private int id;
    private String sender;
    private String requestedBook;
    private String proposedBook;
    private String time;
    private String bookImg;
    private String status;
    private String senderEmail;
    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String  sender) {
        this.sender = sender;
    }

    public String getRequestedBook() {
        return requestedBook;
    }

    public void setRequestedBook(String requestedBook) {
        this.requestedBook = requestedBook;
    }

    public String getProposedBook() {
        return proposedBook;
    }

    public void setProposedBook(String proposedBook) {
        this.proposedBook = proposedBook;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String profileImg) {
        this.bookImg = profileImg;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public Notification(int id, String sender, String requestedBook, String proposedBook, String time, String bookImg, String status, String senderEmail) {
        this.id = id;
        this.sender = sender;
        this.requestedBook = requestedBook;
        this.proposedBook = proposedBook;
        this.time = time;
        this.bookImg = bookImg;
        this.status = status;
        this.senderEmail = senderEmail;
    }
}


