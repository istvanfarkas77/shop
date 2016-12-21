package com.shop;

import lombok.Getter;
import lombok.Setter;

public class BookDTO {

    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    Long author;

    @Getter
    @Setter
    String isbn;
}
