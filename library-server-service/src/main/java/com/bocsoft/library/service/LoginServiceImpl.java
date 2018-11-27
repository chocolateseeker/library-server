package com.bocsoft.library.service;

import com.bocsoft.library.dao.domain.User;
import com.bocsoft.library.dao.mapper.UserMapper;
import com.bocsoft.library.serviceI.LoginService;
import com.bocsoft.library.serviceI.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;

    public UserDTO login(String userId, String password) {
        String inputMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = userMapper.getUser(userId);
        UserDTO userDTO = null;
        if (user != null && inputMD5.equals(user.getPassword())) {
            userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
        }
        return userDTO;
    }
}
