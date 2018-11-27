package com.bocsoft.library.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowInfoHistoryVO {
    private String borrowId;
    private String userId;
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private String borrowTime;
    private String shouldReturnTime;
    private String actualReturnTime;
    private int returned;
    private int renewed;

    private int pageIndex;
    private int limit;
    private long results;
}
