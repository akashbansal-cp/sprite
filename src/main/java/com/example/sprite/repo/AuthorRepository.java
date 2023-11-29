package com.example.sprite.repo;

import com.example.sprite.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    @Query("{name: ?0 }")
    List<Author> findByName(String author);


    @Query("{name: {$regex:?0} }")
    List<Author> findByRegexName(String author);


    @Query("{name: ?0}")
    @Update("{$push:{books:?1}}")
    void updateBooks(String name, String book);
}
