package com.shop;

import lombok.Getter;
import lombok.Setter;

public class AuthorDTO {

    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    String name;

    public AuthorDTO() {
    }

    public AuthorDTO(String name) {
        this.name = name;
    }
}
