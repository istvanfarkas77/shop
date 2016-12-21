package com.shop;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface BookResource {
    @RequestMapping("/book")
    List<BookDTO> get();

    @RequestMapping("/book/title/{title}")
    List<BookDTO> findByTitle(@PathVariable("title") String title);

    @RequestMapping(value = "/book/{author}/{title}/{isbn}", method = RequestMethod.POST)
    @ResponseBody
    BookDTO saveBook(
            @PathVariable("author") long author,
            @PathVariable("title") String title,
            @PathVariable("isbn") String isbn);
}