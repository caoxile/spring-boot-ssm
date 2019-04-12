package com.company.project.auth.service;
import com.company.project.auth.model.Permission;
import com.company.project.common.core.Service;

import java.util.List;
import java.util.Set;


/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
public interface PermissionService extends Service<Permission> {

    // 获取用户所有的权限（列表）
    List<Permission> findUserPermissions(int userId);

    // 获取用户所有的权限标识
    Set<String> findUserStringPermissions(int userId);

    // 获取用户所有的权限ID
    Set<Integer> findUserPermissionIds(int userId);

    // 系统权限的树形结构
    List<Permission> findPermissionsTree();
}
