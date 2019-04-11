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

    List<Permission> findUserPermissions(int userId);

    Set<String> findUserStringPermissions(int userId);
}
