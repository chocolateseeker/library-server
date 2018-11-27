package com.bocsoft.library.serviceI;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UserDTO implements Serializable{
    private static final long serialVersionUID = -5153591053490994722L;
    private String userId;
    private String userName;
    private String department;
    private String team;
    private String role;

}
