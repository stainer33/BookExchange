package com.example.bookexchange1.BLL;

import com.example.bookexchange1.Model.Notification;
import com.example.bookexchange1.Model.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ExchangeTest {

    @Test
    public void add() {
        ExchangeBLL exchangeBLL = new ExchangeBLL();
        boolean result = exchangeBLL.add(1,15, 2, 1, "requested");
        assertEquals(true, result);
    }

    @Test
    public void delete() {


        ExchangeBLL exchangeBLL = new ExchangeBLL();
        boolean result = exchangeBLL.delete(4);
        assertEquals(true, result);
    }



    @Test
    public void notification() {

        List<Notification> myList = new ArrayList<>();
        User.id= 14;
        boolean list;

        ExchangeBLL exchangeBLL = new ExchangeBLL();
        myList = exchangeBLL.notification();
        int size = myList.size();

        if (size>0){
            list = true;
        }
        else {
            list = false;
        }
        assertEquals(true, list);


    }

    @Test
    public void myActivity() {
        List<Notification> myList = new ArrayList<>();
        User.id= 13;
        boolean list;

        ExchangeBLL exchangeBLL = new ExchangeBLL();
        myList = exchangeBLL.myActivity();
        int size = myList.size();

        if (size>0){
            list = true;
        }
        else {
            list = false;
        }
        assertEquals(true, list);

    }
}