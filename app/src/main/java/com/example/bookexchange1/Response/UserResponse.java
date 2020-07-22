package com.example.bookexchange1.Response;

public class UserResponse {
    private String status;
 //   private String token;
    private Success success;
    public static String Token="";//to check logged in or not

    public UserResponse(String status, Success success) {
        this.status = status;
        this.success = success;
    }
//    public UserResponse(String status, String token) {
//        this.status = status;
//        this.token = token;
//
//    }


    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }

}

 class Success{
    String token;

    public Success(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
