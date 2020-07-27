package com.example.bookexchange1.Model;

public class User {

    public  static String t_email;
    public static int id;
    private String email;
    private String fullName;
    private String mobileNo;
    private String profileImg;
    private String address;


    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String email, String fullName, String mobileNo, String address,String profileImg) {
        this.email = email;
        this.fullName = fullName;

        this.mobileNo = mobileNo;
        this.address = address;
        this.profileImg=profileImg;
    }
}
