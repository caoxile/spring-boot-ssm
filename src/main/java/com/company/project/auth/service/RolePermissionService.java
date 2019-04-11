package com.company.project.auth.service;
import com.company.project.auth.model.RolePermission;
import com.company.project.common.core.Service;


/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
public interface RolePermissionService extends Service<RolePermission> {

    void saveRolePermission(RolePermission rolePermission);

    void deleteRolePermission(RolePermission rolePermission);
}
