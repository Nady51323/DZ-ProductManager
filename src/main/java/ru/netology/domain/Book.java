package ru.netology.domain;

import lombok.Data;

public class Book extends Product {
    private String author;

    public Book() {
        super();
    }

    public Book(int id, String productName, int productPrice, String author) {
        super(id, productName, productPrice);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
