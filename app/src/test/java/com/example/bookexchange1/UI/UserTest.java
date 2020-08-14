package com.example.bookexchange1.UI;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void register() {



        UserBLL userBLL = new UserBLL();
        boolean result = userBLL.signUp("ram1@gmail.com","ram lama", "12345678", "1234567890", "thaiba");
        assertEquals(true, result);


    }

    @Test
    public void profile(){


        User.t_email= "ram@gmail.com";

        UserBLL userBLL = new UserBLL();

        User user;
        user = userBLL.profile();
        assertEquals("ram lama", user.getFullName());



    }

}