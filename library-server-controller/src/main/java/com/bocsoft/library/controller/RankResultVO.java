package com.bocsoft.library.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RankResultVO {
    private List<BookRankVO> bookList;
    private List<UserRankVO> userList;
}
