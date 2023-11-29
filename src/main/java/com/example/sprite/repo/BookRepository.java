package com.example.sprite.repo;

import com.example.sprite.model.Book;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    @Query("{isbn:?0}")
    List<Book> findByISBN(String bookISBN);

    @Query("{copiesAvailable:{$gte:?0}}")
    List<Book> findByMinCopies(int minCopiesAvailable);

    @Query("{genres : {$in:[?0]}}")
    List<Book> findByGenre(String genre);


    @Query("{copiesAvailable:{$gte:?1},authors : {$in:[?0]}}")
    List<Book> findByAuthorAndCopiesAvailable(ObjectId authorId, int minCopiesAvailable);

    @Query("{copiesAvailable:{$gte:?1},genres : {$in:[?0]}}")
    List<Book> findByGenreAndCopiesAvailable(String genre, int minCopiesAvailable);

    @Query("{genres : {$in:[?0]},authors : {$in:[?1]}}")
    List<Book> findByGenreAndAuthor(String[] genres, String[] authors);


    @Query("{?0:{$in:?1}}")
    List<Book> filterBooks(String field, List<?> value);
}
