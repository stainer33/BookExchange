package com.example.bookexchange1.Response;

public class UserResponse {
    private String status;
    private String token;

    public static String Token="";//to check logged in or not


    public UserResponse(String status, String token) {
        this.status = status;
        this.token = token;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
