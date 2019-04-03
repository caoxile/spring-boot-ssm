package com.company.project.auth.service.impl;

import com.company.project.auth.dao.UserMapper;
import com.company.project.auth.model.User;
import com.company.project.auth.service.UserService;
import com.company.project.common.core.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @Author CodeGenerator
 * @Create 2019-04-03
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
