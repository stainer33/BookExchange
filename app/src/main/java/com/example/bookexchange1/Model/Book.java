package com.example.bookexchange1.Model;

public class Book {
    private String name;
    private String author;
    private int image;

    public Book(String name, String author, int image) {
        this.name = name;
        this.author = author;
        this.image= image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
