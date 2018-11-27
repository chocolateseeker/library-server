package com.bocsoft.library.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchBookConditionVO {
    private String bookName;
    private String publisher;
    private String author;
    private String owner;
    private String type;
    private int rest;
    private int pageIndex;
    private int limit;
}
