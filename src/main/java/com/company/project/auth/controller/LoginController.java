package com.company.project.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.company.project.auth.constants.AuthConstants;
import com.company.project.auth.model.User;
import com.company.project.auth.service.LoginService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject loginUser){
        String loginName = loginUser.getString("loginName");
        String password = loginUser.getString("password");
        User user = loginService.login(loginName,password);
        String token = JwtTokenUtil.generateToken(user);
        return ResultGenerator.genSuccessResult(token);
    }

    @PostMapping("/refresh/token")
    public Result refreshToken(HttpServletRequest request){
        String refreshToken = JwtTokenUtil.refreshToken(request.getHeader(AuthConstants.HEADER_AUTH));
        return ResultGenerator.genSuccessResult(refreshToken);
    }

}
