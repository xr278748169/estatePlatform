package com.kerry.member.controler;

import com.kerry.member.dto.Login;
import com.kerry.member.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录处理
 * Created by wangshen on 2017/5/26.
 */
@RestController
@RequestMapping("/api/sys")
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * 用户登录
     * @param login
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody Login login) {
        return loginService.login(login);
    }

}
