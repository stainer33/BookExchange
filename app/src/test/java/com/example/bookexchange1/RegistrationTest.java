package com.example.bookexchange1;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RegistrationTest {

    @Test
    public void onCreate() {

        User user = new User("sujitkhadgi@gmail.com", "Test Sujit", "12345678", "1234567890", "thaiba", "img");
        UserBLL userBLL = new UserBLL();
        boolean result = userBLL.signUp(user);
        assertEquals(true, result);

    }
}