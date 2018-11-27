package com.bocsoft.library.serviceI;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BorrowInfoHistoryDTO implements Serializable {
    private static final long serialVersionUID = 3120122769420233405L;
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
