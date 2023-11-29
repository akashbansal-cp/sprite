package com.example.sprite.controller;


import com.example.sprite.dto.BookDTO;
import com.example.sprite.service.AuthorService;
import com.example.sprite.service.BookService;
import com.example.sprite.model.Author;
import com.example.sprite.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    BookService bookService = new BookService();
    AuthorService authorService = new AuthorService();

    @PostMapping("/addbook")
    public String addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookDTO.getBook();
        if (book.isValid() == false) {
            return "Book is Invalid. Please reconsider the data.";
        }
        List<String> authors = bookDTO.getAuthors();
        //validate isbn
        String bookISBN = book.getIsbn();
        try {
            List<Book> books = bookService.findByISBN(bookISBN);
            if (books.size() > 0) {
                throw new Exception("ISBN already exists");
            }
        } catch (Exception e) {
            return "Operation Unsuccessful." + e.getMessage();
        }
        // convert list of authors to list of id
        if (authors.size() == 0) {
            return "Ohh bro! someone needs to write the book so provide me with the author who wrote this book.";
        }
        for (String author : authors) {
            try {
                List<Author> _authors = authorService.findByName(author);
                if (_authors.size() == 0) {
                    return "Author " + author + " Not found in the database. Please Consider adding all the authors to database.";
                }
                if (_authors.size() > 1) {
                    return "Multiple authors found with same name. Contact Akash 👍";
                }
                book.addAuthors(_authors.get(0).getAuthorId());
            } catch (Exception e) {
                System.out.println(e);
                return "Some error occurred";
            }
        }
        for (String author : authors) {
            List<Author> _authors = authorService.findByName(author);
            authorService.updateBooks(author, bookISBN);
        }
        bookService.save(book);
        return "add book called";
    }

    @GetMapping("/all-books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{minCopiesAvailable}")
    public List<Book> getApi(@PathVariable int minCopiesAvailable, @RequestParam(required = false) String genre) {
        System.out.println(minCopiesAvailable);
        return bookService.findByGenreAndCopiesAvailable(genre, minCopiesAvailable);

    }

    @GetMapping("/books")
    public List<Book> filterBooks(@RequestParam(required = false) String genre, @RequestParam(required = false) String author) {
        if (genre == null && author != null) {
            List<Author> authors = authorService.findByName((author));
            if (authors.size() == 0) return null;
            return bookService.filterBooks("authors", List.of(authors.get(0).getAuthorId()));
        } else if (genre != null && author == null) {
            return bookService.filterBooks("genres", List.of(genre));
        }
        return null;
    }

    @GetMapping("/regex-books")
    public List<Author> regexBooks(@RequestParam(required = false) String author) {
        return authorService.findByRegexName((author));
    }





}
