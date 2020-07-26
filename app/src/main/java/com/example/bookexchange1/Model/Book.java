package com.example.bookexchange1.Model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private Owner owner;
    private String image;
    private String des;

    public Book(int id,String name, String author, String image,Owner owner,String des) {
        this.id=id;
        this.name = name;
        this.author = author;
        this.image= image;
        this.owner=owner;
        this.des=des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
