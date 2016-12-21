package com.shop.web.lib;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shop.AuthorDTO;
import com.shop.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
class WebLibService {

    @Autowired
    AuthorClient authorClient;

    @Autowired
    BookClient bookClient;

    @Autowired
    private KafkaTemplate<Integer, BookDTO> template;

    @HystrixCommand(fallbackMethod = "findDefaultLib")
    public List<Lib> findByTitle(String title) {

        List<BookDTO> bookEntities = null;
        List<Lib> libs = new ArrayList();

        bookEntities = bookClient.findByTitle(title);

        for (BookDTO bookEntity : bookEntities) {
            AuthorDTO author = authorClient.findById(bookEntity.getAuthor());

            libs.add(new Lib(bookEntity.getId(), bookEntity.getTitle(), author.getName(), bookEntity.getIsbn()));

            template.send("fooTopic", 0, bookEntity);
        }

        return libs;
    }

    public List<Lib> findDefaultLib(String title) {
        List<Lib> libs = new ArrayList();

        libs.add(new Lib(0, "Default library item", "Default author", "Default ISBN"));

        return libs;
    }

    @Transactional
    public Lib saveBook(String authorName, String title, String isbn) {
        BookDTO book = null;
        AuthorDTO author = null;

        try {
            //author = restTemplate.getForObject("http://AUTHOR-SERVICE/author/name/{name}", Author.class, authorName);
            author = authorClient.findByName(authorName);

            if (author == null ) {
                //author = restTemplate.postForEntity("http://AUTHOR-SERVICE/author", new Author(authorName), Author.class).getBody();
                author = authorClient.save(new AuthorDTO(authorName));
            }

            if (author == null) {
                author = new AuthorDTO("DEFAULT AUTHOR DTO");
                author.setId(-1L);
            }

            //book = restTemplate.postForEntity("http://BOOK-SERVICE/book/{author}/{title}/{isbn}", null, Book.class, author.getId(), title, isbn).getBody();
            book = bookClient.saveBook(author.getId(), title, isbn);

            if (book == null) {
                book = new BookDTO();
                book.setId(-1L);
                book.setAuthor(author.getId());
                book.setIsbn("NO ISBN");
                book.setTitle("NO TITLE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Lib(book.getId(), book.getTitle(), author.getName(), book.getIsbn());
    }
}
