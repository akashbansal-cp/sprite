package com.example.sprite.dto;

import com.example.sprite.model.Book;

import java.util.List;

public class BookDTO {
    private Book book;
    private List<String> authors;

    public Book getBook() {
        return this.book;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

}
