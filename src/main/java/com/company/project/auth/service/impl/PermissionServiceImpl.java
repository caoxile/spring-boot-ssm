package com.company.project.auth.service.impl;

import com.company.project.auth.dao.PermissionMapper;
import com.company.project.auth.model.Permission;
import com.company.project.auth.service.PermissionService;
import com.company.project.common.core.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public Set<Integer> findUserPermissionIds(int userId) {
        return findUserPermissions(userId).stream().map(Permission::getId).collect(Collectors.toSet());
    }

    @Override
    public List<Permission> findPermissionsTree() {
        List<Permission> permissionList = this.findAll();
        List<Permission> treeList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(permissionList)){
            for(Permission permission:permissionList){
                if(permission.getParentId()==0){
                    treeList.add(permission);
                }
                for(Permission perm:permissionList){
                    if(perm.getParentId().compareTo(permission.getId())==0){
                        if(permission.getChildren()==null){
                            permission.setChildren(new ArrayList<>());
                        }
                        permission.getChildren().add(perm);
                    }
                }
            }
        }
        return treeList;
    }
}
