package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class BookController implements BookResource {

    @Autowired
    BookService bookService;

    @Override
    public List<BookDTO> get() {
        return bookService.findAll();
    }

    @Override
    public List<BookDTO> findByTitle(@PathVariable("title") String title) {
        return bookService.findByTitle(title);
    }

    @Override
    public BookDTO saveBook(
            @PathVariable("author") long author,
            @PathVariable("title") String title,
            @PathVariable("isbn") String isbn) {
        return bookService.saveBook(author, title, isbn);
    }
}