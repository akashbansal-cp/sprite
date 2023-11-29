package com.example.sprite.service;

import com.example.sprite.repo.AuthorRepository;
import com.example.sprite.model.Author;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findByRegexName(String author) {
        return authorRepository.findByRegexName(author);
    }

    public void updateBooks(String author, String bookISBN) {
        authorRepository.updateBooks(author, bookISBN);
    }

    public List<Author> findByName(String author) {
        return authorRepository.findByName((author));
    }

}
