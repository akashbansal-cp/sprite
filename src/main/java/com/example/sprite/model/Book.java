package com.example.sprite.model;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "books")
public class Book {

    private String isbn;
    private String title;
    private int copiesAvailable;
    private List<String> genres;
    private List<ObjectId> authors = new ArrayList<ObjectId>();

    public Book(){};

    public Book(String isbn, String title, List<String> genres, int copiesAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.genres = genres;
        this.copiesAvailable = copiesAvailable;
    }

    public boolean isValid() {
        if (this.title != null && this.genres != null && this.genres.size() != 0 && this.isbn != null && copiesAvailable >= 0) {
            return true;
        }
        return false;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }


    public String getTitle(String bookId) {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres=genres;
    }

    public List<ObjectId> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<ObjectId> authors) {
        this.authors=authors;
    }

    public void addAuthors(ObjectId author) {
        this.authors.add(author);
    }


    public void print() {
        System.out.println("ISBN : " + this.isbn);
        System.out.println("Title : " + this.title);
        System.out.println("Copies Available : " + this.copiesAvailable);
        System.out.print("Genre : ");
        genres.forEach(e -> {
            System.out.print(e + " ");
        });
        System.out.println();
        System.out.print("Authors : ");
        authors.forEach(e -> {
            System.out.print(e + " ");
        });
        System.out.println();
    }



}
