package com.bocsoft.library.serviceI;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BookRankDTO implements Serializable {
    private static final long serialVersionUID = -8196276609936647896L;
    private String bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String borrowedCount;
}
