package com.bocsoft.library.controller;

import com.bocsoft.library.serviceI.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResultVO {
    private int state;
    private String indexUrl;
    private UserDTO userInfo;
}
