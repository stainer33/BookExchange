package com.example.bookexchange1.BLL;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExchangeTest {

    @Test
    public void add() {
        ExchangeBLL exchangeBLL = new ExchangeBLL();
        boolean result = exchangeBLL.add(1,15, 2, 1, "requested");
        assertEquals(true, result);
    }

    @Test
    public void delete() {
    }

    @Test
    public void notification() {
    }

    @Test
    public void myActivity() {

    }
}