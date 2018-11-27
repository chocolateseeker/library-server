package com.bocsoft.library.serviceI;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserRankDTO implements Serializable {
    private static final long serialVersionUID = -6742299886266952602L;
    private String userId;
    private String userName;
    private String department;
    private String team;
    private int borrowCount;
}
