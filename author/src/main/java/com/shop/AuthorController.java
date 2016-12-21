package com.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
class AuthorController implements AuthorResource {

    AuthorService authorService;

    String aaa;

    @Autowired
    public AuthorController(AuthorService authorService, String aaa) {
        this.authorService = authorService;
        this.aaa = aaa;
    }

    @Override
    public AuthorDTO findByName(@PathVariable("name") String name) {
        return authorService.findByName(name);
    }

    @Override
    public AuthorDTO findById(@PathVariable("id") Long id) {
        return authorService.findById(id);
    }

    @Override
    public AuthorDTO save(@RequestBody AuthorDTO author) {
        return authorService.save(author.getName());
    }

    @Override
    public List<AuthorDTO> get() {
        List<AuthorDTO> a = new ArrayList<AuthorDTO>();
        a.addAll(authorService.get());
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(-999L);
        authorDTO.setName(aaa);
        a.add(authorDTO);

        return a;
    }
}