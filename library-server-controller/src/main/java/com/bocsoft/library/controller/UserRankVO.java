package com.bocsoft.library.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRankVO {
    private String userName;
    private String userId;
    private String department;
    private String team;
    private int borrowCount;
}
