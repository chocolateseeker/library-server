package com.bocsoft.library.dao.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {
    private String bookId;
    private String bookName;
    private String publisher;
    private String author;
    private String type;
    private String owner;
    private String bookshelf;
    private int total;
    private int rest;

}
