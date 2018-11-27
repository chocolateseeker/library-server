package com.bocsoft.library.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRankVO {
    private String bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String borrowedCount;
}
