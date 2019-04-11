package com.company.project.auth.service.impl;

import com.company.project.auth.dao.PermissionMapper;
import com.company.project.auth.model.Permission;
import com.company.project.auth.service.PermissionService;
import com.company.project.common.core.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
@Service
@Transactional
public class PermissionServiceImpl extends BaseService<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findUserPermissions(int userId) {
        return permissionMapper.findUserPermissions(userId);
    }

    @Override
    public Set<String> findUserStringPermissions(int userId) {
        return findUserPermissions(userId).stream().map(Permission::getPermissionCode).collect(Collectors.toSet());
    }

}
