package com.bocsoft.library.controller;

import com.bocsoft.library.serviceI.SearchBookResultDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SearchBookResultVO {
    private List<SearchBookResultDTO> books;

    private int pageIndex;
    private int limit;
    private long results;
}
