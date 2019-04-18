package com.company.project.auth.service.impl;

import com.company.project.auth.dao.UserRoleMapper;
import com.company.project.auth.model.UserRole;
import com.company.project.auth.service.UserRoleService;
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
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void saveUserRole(UserRole userRole) {
        Assert.notNull(userRole.getUserId(),"用户ID");
        Assert.notNull(userRole.getRoleId(),"角色ID");
        try {
            userRole.setId(null);
            this.save(userRole);
        }catch (Exception e){
            throw new ServiceException("设置用户角色失败");
        }
    }

    @Override
    public void deleteUserRole(UserRole userRole) {
        Assert.notNull(userRole.getUserId(),"用户ID");
        Assert.notNull(userRole.getRoleId(),"角色ID");
        UserRole ur = findUserRole(userRole.getUserId(),userRole.getRoleId());
        if(ur==null){
            throw new ServiceException("设置用户角色失败");
        }
        this.deleteById(ur.getId());
    }

    private UserRole findUserRole(int userId,int roleId){
        Condition condition = new Condition(UserRole.class);
        condition.createCriteria().andEqualTo("userId",userId).andEqualTo("roleId",roleId);
        List<UserRole> urList = this.findByCondition(condition);
        if(!CollectionUtils.isEmpty(urList)){
            return urList.get(0);
        }
        return null;
    }
}
