package com.shop;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    Book findByIsbn(String isbn);

    @Query("select count(*) from Book")
    long countBooks();

    Book save(Book book);
}
