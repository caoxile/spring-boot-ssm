package com.company.project.auth.service.impl;

import com.company.project.auth.dao.RolePermissionMapper;
import com.company.project.auth.model.RolePermission;
import com.company.project.auth.service.RolePermissionService;
import com.company.project.common.core.BaseService;
import com.company.project.common.core.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends BaseService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void saveRolePermission(RolePermission rolePermission) {
        Assert.notNull(rolePermission.getRoleId(),"角色ID");
        Assert.notNull(rolePermission.getPermissionId(),"权限ID");
        try {
            rolePermission.setId(null);
            this.save(rolePermission);
        }catch (Exception e){
            throw new ServiceException("设置角色权限失败");
        }
    }

    @Override
    public void deleteRolePermission(RolePermission rolePermission) {
        Assert.notNull(rolePermission.getRoleId(),"角色ID");
        Assert.notNull(rolePermission.getPermissionId(),"权限ID");
        RolePermission rp = findRolePermission(rolePermission.getRoleId(),rolePermission.getPermissionId());
        if(rp==null){
            throw new ServiceException("设置角色权限失败");
        }
        this.deleteById(rp.getId());
    }

    private RolePermission findRolePermission(int roleId,int permissionId){
        Condition condition = new Condition(RolePermission.class);
        condition.createCriteria().andEqualTo("permissionId",permissionId).andEqualTo("roleId",roleId);
        List<RolePermission> urList = this.findByCondition(condition);
        if(!CollectionUtils.isEmpty(urList)){
            return urList.get(0);
        }
        return null;
    }
}
