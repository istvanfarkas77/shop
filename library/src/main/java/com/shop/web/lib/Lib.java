package com.shop.web.lib;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

class Lib implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter @Getter
    private long id;

    @Setter @Getter
    private String title;

    @Setter @Getter
    private String author;

    @Setter @Getter
    private String isbn;

    public Lib() {
    }

    public Lib(long id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
