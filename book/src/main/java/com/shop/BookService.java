package com.shop;

import org.apache.commons.collections.IteratorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BookService {

    protected Logger logger = Logger.getLogger(BookController.class.getName());

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<BookDTO> findAll() {
        List<Book> books = IteratorUtils.toList(bookRepository.findAll().iterator());

        List<BookDTO> booksDTO = books.stream().map(pojo -> modelMapper.map(pojo, BookDTO.class)).collect(Collectors.toList());

        return booksDTO;
    }

    @Cacheable(cacheNames = {"bookCache"})
    public List<BookDTO> findByTitle(String title) {
        logger.info("### Not from cache ###");

        List<Book> books = (List<Book>)bookRepository.findByTitle(title);

        List<BookDTO> booksDTO = books.stream().map(pojo -> modelMapper.map(pojo, BookDTO.class)).collect(Collectors.toList());

        return booksDTO;
    }

    public BookDTO saveBook(long author, String title, String isbn) {

        Book book = new Book();

        book.setAuthor(author);
        book.setTitle(title);
        book.setIsbn(isbn);

        return modelMapper.map(bookRepository.save(book), BookDTO.class);
    }
}
