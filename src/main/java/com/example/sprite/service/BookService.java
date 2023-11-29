package com.example.sprite.service;

import com.example.sprite.repo.BookRepository;
import com.example.sprite.model.Author;
import com.example.sprite.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findByISBN(String bookISBN) {
        return bookRepository.findByISBN(bookISBN);
    }

    public List<Book> findByGenreAndCopiesAvailable(String genre, int minCopiesAvailable) {
        return bookRepository.findByGenreAndCopiesAvailable(genre, minCopiesAvailable);
    }

    public List<Book> filterBooks(String field, List<?> value) {
        return bookRepository.filterBooks(field, value);
    }

}
