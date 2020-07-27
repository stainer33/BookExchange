package com.example.bookexchange1;

import com.example.bookexchange1.BLL.BookBLL;
import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Model.User;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class AddBookTest {


    @Test
    public void Postbook(){


        BookBLL bookBLL = new BookBLL();
        boolean result = bookBLL.add("kalo pothi", "kali", "test story",book1.jpg, "new", 2);
        assertEquals(true, result);

    }

}