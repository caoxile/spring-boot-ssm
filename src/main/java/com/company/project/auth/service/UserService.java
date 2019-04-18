package com.company.project.auth.service;
import com.company.project.auth.model.User;
import com.company.project.common.core.Service;

import java.util.List;


/**
 * @Author CodeGenerator
 * @Create 2019-04-03
 */
public interface UserService extends Service<User> {
    List<User> findUsersWithRoleName();

    void addUser(User user);

    void updateUser(User user) throws Exception;

    void deleteUser(Integer id);
}
