package com.example.sprite.service;


import com.example.sprite.model.Author;
import com.example.sprite.repo.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveNewAuthor() {
        Author inputAuthor = new Author("John Doe", "addL1", "addL2");
        when(authorRepository.save(inputAuthor)).thenReturn(inputAuthor);

        Author result = authorService.saveNewAuthor(inputAuthor);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(authorRepository, times(1)).save(inputAuthor);
    }

    @Test
    void testGetAuthorsByNameRegex() {
        String nameRegex = "John.*";

        List<Author> mockAuthors = new ArrayList<>();
        mockAuthors.add(new Author("John Doe", "addL1", "addL2"));
        mockAuthors.add(new Author("Johnny Bravo", "addl1", "addl2"));

        when(authorRepository.findByRegexName(nameRegex)).thenReturn(mockAuthors);

        List<Author> result = authorService.findByRegexName(nameRegex);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Johnny Bravo", result.get(1).getName());
        verify(authorRepository, times(1)).findByRegexName(nameRegex);
    }


    @Test
    void testGetAuthorByName() {
        String existingName = "John";
        Author mockAuthor = new Author("John Doe", "addL1", "addL2");
        when(authorRepository.findByName(existingName)).thenReturn(List.of(mockAuthor));
        Author result = authorService.findByName(existingName).get(0);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(authorRepository, times(1)).findByName(existingName);
    }

    @Test
    void testAddBooks() {
        // implementation left
    }
}