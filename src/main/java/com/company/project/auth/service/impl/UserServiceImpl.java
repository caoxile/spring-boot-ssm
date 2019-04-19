package com.company.project.auth.service.impl;

import com.company.project.auth.dao.UserMapper;
import com.company.project.auth.model.User;
import com.company.project.auth.service.RoleService;
import com.company.project.auth.service.UserService;
import com.company.project.common.core.BaseService;
import com.company.project.common.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author CodeGenerator
 * @Create 2019-04-08
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    RoleService roleService;

    @Override
    public List<User> findUsersWithRoleName() {
        List<User> userList = this.findActiveUsers();
        if(!CollectionUtils.isEmpty(userList)){
            for(User user:userList){
                user.setRoles(roleService.findUserStringRoles(user.getId()));
            }
        }
        return userList;
    }

    public List<User> findActiveUsers(){
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("status","1");
        return this.findByCondition(condition);
    }

    @Override
    public void addUser(User user) {
        Assert.hasText(user.getNickname(),"昵称不能为空");
        Assert.hasText(user.getUsername(),"用户名不能为空");
        Assert.hasText(user.getPassword(),"密码不能为空");
        String pwd = SHA256Util.encrypt(user.getPassword().trim());
        user.setPassword(pwd);
        this.save(user);
    }

    @Override
    public void updateUser(User user) {
        Assert.notNull(user.getId(),"用户ID");
        User uptUser = new User();
        uptUser.setId(user.getId());
        if(!StringUtils.isEmpty(user.getNickname())){
            uptUser.setNickname(user.getNickname());
        }
        if(!StringUtils.isEmpty(user.getPassword())){
            uptUser.setPassword(SHA256Util.encrypt(user.getPassword()));
        }
        this.update(uptUser);
    }

    // 逻辑删除
    public void deleteUser(Integer id) {
        Assert.notNull(id,"用户ID");
        User uptUser = new User();
        uptUser.setId(id);
        uptUser.setStatus("0");
        this.update(uptUser);
    }
}
