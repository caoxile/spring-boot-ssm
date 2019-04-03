package com.company.project.auth.service;

import com.company.project.common.core.Result;

/**
 * @Package com.company.project.common.core.auth
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-24
 */
public interface LoginService {

    Result login(String username, String password) throws Exception;

    Result getInfo();

    Result logout();
}
