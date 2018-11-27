package com.bocsoft.library.dao.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BorrowInfo {
    private String borrowId;
    private String userId;
    private String bookId;
    private String borrowTime;
    private String shouldReturnTime;
    private String actualReturnTime;
    private int returned;
    private int renewed;


}
