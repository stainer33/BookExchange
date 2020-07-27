package com.example.bookexchange1.Model;

public class Notification {
    private int id;
    private String sender;
    private  String requestedBook;
    private String proposedBook;
    private String time;
    private int profileImg;
    private String status;

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

    public void setSender(String sender) {
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

    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }

    public Notification(int id,String sender, String requestedBook, String proposedBook, String time, int profileImg,String status) {
        this.id=id;
        this.sender = sender;
        this.requestedBook = requestedBook;
        this.proposedBook = proposedBook;
        this.time = time;
        this.profileImg = profileImg;
        this.status=status;
    }
}
