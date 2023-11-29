package com.example.sprite.controller;


import com.example.sprite.dto.AuthorDTO;
import com.example.sprite.service.AuthorService;
import com.example.sprite.model.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @PostMapping("/addauthor")
    public String addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = authorDTO.getAuthor();
        AuthorService authorService = new AuthorService();
        if (author.isValid() == false) {
            return "Author is Invalid. Please reconsider the data.";
        }
        author.print();
        authorService.saveNewAuthor(author);
        return "add author called";
    }
}
