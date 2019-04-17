package com.company.project.auth.service.impl;

import com.company.project.auth.dao.RoleMapper;
import com.company.project.auth.model.Role;
import com.company.project.auth.service.RoleService;
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
public class RoleServiceImpl extends BaseService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findUserRoles(int userId) {
        return roleMapper.findUserRoles(userId);
    }

    @Override
    public Set<String> findUserStringRoles(int userId) {
        return findUserRoles(userId).stream().map(Role::getRoleName).collect(Collectors.toSet());
    }
}
