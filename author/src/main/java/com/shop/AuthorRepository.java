package com.shop;

import org.springframework.data.repository.CrudRepository;

interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
