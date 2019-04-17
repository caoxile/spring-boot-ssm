package com.company.project.auth.dao;

import com.company.project.auth.model.Role;
import com.company.project.common.core.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    List<Role> findUserRoles(int userId);
}