package com.example.bookexchange1;



import org.junit.Test;

import Models.User;
import bll.RegistrationBLL;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;


public class RegistrationTest {

    @Test
    public void onCreate() {


        User user = new User("sujitkhadgi@gmail.com", "Test Sujit", "12345678", "1234567890", "thaiba", "img");
        UserBLL userBLL = new UserBLL();
        boolean result = userBLL.signUp(user);
        assertEquals(true, result);


    }
}