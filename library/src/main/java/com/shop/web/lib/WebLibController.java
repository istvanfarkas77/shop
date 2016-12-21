package com.shop.web.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@EnableCircuitBreaker
@RequestMapping("/lib")
class WebLibController {

    @Autowired
    WebLibService webBookService;

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
    public String findByTitle(Model model, @PathVariable("title") String title) {

        List<Lib> books = webBookService.findByTitle(title);

        model.addAttribute("books", books);

        return "libs";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveBook(
            Model model,
            Lib book) {

        Lib savedBook = webBookService.saveBook(book.getAuthor(), book.getTitle(), book.getIsbn());

        model.addAttribute("book", savedBook);

        return "libDetail";
    }
}