package com.bocsoft.library.dao.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRank {
    private String userId;
    private String userName;
    private String department;
    private String team;
    private int borrowCount;
}
