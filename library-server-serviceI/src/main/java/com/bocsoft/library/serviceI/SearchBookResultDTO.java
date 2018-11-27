package com.bocsoft.library.serviceI;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SearchBookResultDTO implements Serializable {
    private static final long serialVersionUID = 3653537443593927382L;
    private String bookId;
    private String bookName;
    private String publisher;
    private String author;
    private String type;
    private String owner;
    private int total;
    private int rest;
    private String bookshelf;

    private int pageIndex;
    private int limit;
    private long results;

}
