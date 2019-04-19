package com.company.project.auth.dao;

import com.company.project.auth.model.Permission;
import com.company.project.common.core.Mapper;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {
    List<Permission> findUserPermissions(int userId);

    List<Permission> findRolePermissions(Integer roleId);
}