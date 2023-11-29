package com.example.sprite.service;

import com.example.sprite.model.Author;
import com.example.sprite.model.Book;
import com.example.sprite.repo.AuthorRepository;
import com.example.sprite.repo.BookRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorService authorService;
    @Mock
    private AuthorRepository authorRepository;

    private final List<Book> expectedBooks = Arrays.asList(new Book("123", "Title2",List.of("Science Fiction"), 8),
            new Book("236","Title1", List.of("Science Fiction"), 10));



    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(expectedBooks);
        List<Book> result = bookService.getAllBooks();
        assertEquals(expectedBooks, result);
    }

    @Test
    void testGetBookByIsbn() {
        String bookIsbn = "123";
        Book expectedBook = new Book(bookIsbn, "T1",List.of("tech"), 8);
        when(bookRepository.findByISBN(bookIsbn)).thenReturn(List.of(expectedBook));
        List<Book> result = bookService.findByISBN(bookIsbn);
        assertEquals(1, result.size());
    }

    @Test
    void testGetBooksByGenreAndCopiesAvailable() {
        String genre = "Science Fiction";
        int copiesAvailable = 5;
        when(bookRepository.findByGenreAndCopiesAvailable(genre, copiesAvailable))
                .thenReturn(expectedBooks);
        List<Book> result = bookService.findByGenreAndCopiesAvailable(genre, copiesAvailable);
        assertEquals(2, result.size());
    }

    @Test
    void testGetBooksByGenre() {
        String genre = "Science Fiction";
        when(bookRepository.filterBooks("genres",List.of(genre))).thenReturn(expectedBooks);
        List<Book> result = bookService.filterBooks("genres",List.of(genre));
        assertEquals(2, result.size());
    }
}
