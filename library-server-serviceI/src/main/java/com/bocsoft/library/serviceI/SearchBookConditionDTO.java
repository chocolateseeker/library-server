package com.bocsoft.library.serviceI;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SearchBookConditionDTO implements Serializable{

    private static final long serialVersionUID = -8155093557015289007L;
    private String bookName;
    private String publisher;
    private String author;
    private String type;
    private String owner;
    private int rest;
    private int pageIndex;
    private int limit;
}
