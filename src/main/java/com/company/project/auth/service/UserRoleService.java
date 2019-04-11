package com.company.project.auth.service;
import com.company.project.auth.model.UserRole;
import com.company.project.common.core.Service;


/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
public interface UserRoleService extends Service<UserRole> {

    void saveUserRole(UserRole userRole);

    void deleteUserRole(UserRole userRole);
}
