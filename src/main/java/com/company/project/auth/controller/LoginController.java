package com.company.project.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.company.project.auth.service.LoginService;
import com.company.project.common.core.Result;
import com.company.project.common.log.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.company.project.auth.controller
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-24
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @SystemLog("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody JSONObject loginUser) throws Exception {
        String username = loginUser.getString("username");
        String password = loginUser.getString("password");
        return loginService.login(username, password);
    }

    /**
     * 查询当前登录用户的信息
     */
    @PostMapping("/info")
    public Result getInfo() {
        return loginService.getInfo();
    }

    /**
     * 登出
     */
    @SystemLog("用户登出")
    @PostMapping("/logout")
    public Result logout() {
        return loginService.logout();
    }

}
