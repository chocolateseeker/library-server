package com.bocsoft.library.dao.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRank {
    private String bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String borrowedCount;
}
