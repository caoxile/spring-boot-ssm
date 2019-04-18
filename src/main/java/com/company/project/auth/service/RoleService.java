package com.company.project.auth.service;
import com.company.project.auth.model.Role;
import com.company.project.common.core.Service;

import java.util.List;
import java.util.Set;


/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
public interface RoleService extends Service<Role> {

    // 获取用户所有的角色（列表）
    List<Role> findUserRoles(int userId);

    // 获取用户所有的角色名称
    Set<String> findUserStringRoles(int userId);
}
