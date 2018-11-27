package com.bocsoft.library.controller;

import com.bocsoft.library.serviceI.LoginService;
import com.bocsoft.library.serviceI.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/library")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = {"/userLogin"}, method = RequestMethod.POST)
    @ResponseBody
    public LoginResultVO login(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        String userId = loginVO.getUserId();
        String password = loginVO.getPassword();

        HttpSession session = request.getSession();
        LoginResultVO loginResultVO = new LoginResultVO();
        if (userId == null || "".equals(userId)) {
            //用户名为空
            loginResultVO.setState(-1);
        } else if (password == null || "".equals(password)) {
            //密码为空
            loginResultVO.setState(-2);
        } else {
            UserDTO userDTO = loginService.login(userId, password);
            if (userDTO == null) {
                //用户名或密码错误
                loginResultVO.setState(-3);
            } else {
                loginResultVO.setState(0);
                session.setAttribute("user", userDTO);
                loginResultVO.setIndexUrl(request.getContextPath() + "/library/index");
                loginResultVO.setUserInfo(userDTO);
            }
        }
        return loginResultVO;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public int logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("user");
        return 0;
    }

    @RequestMapping("/getSessionUserInfo")
    @ResponseBody
    public Object getSessionUserInfo(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession().getAttribute("user");
    }

    @RequestMapping("/loginConfirm")
    @ResponseBody
    public int loginConfirm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("user");
        if (userInfo == null) {
            return -1;
        } else {
            return 0;
        }
    }

}
