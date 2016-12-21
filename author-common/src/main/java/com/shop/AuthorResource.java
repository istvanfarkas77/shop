package com.shop;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AuthorResource {

    @RequestMapping("/author/name/{name}")
    AuthorDTO findByName(@PathVariable("name") String name);

    @RequestMapping("/author/id/{id}")
    AuthorDTO findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/author", method = RequestMethod.POST)
    @ResponseBody
    AuthorDTO save(@RequestBody AuthorDTO author);

    @RequestMapping("/author")
    List<AuthorDTO> get();
}
