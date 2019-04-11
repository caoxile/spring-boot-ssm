package com.company.project.auth.controller;

import com.company.project.auth.model.Role;
import com.company.project.auth.model.RolePermission;
import com.company.project.auth.service.RolePermissionService;
import com.company.project.auth.service.RoleService;
import com.company.project.common.core.BaseController;
import com.company.project.common.core.Result;
import com.company.project.common.core.ResultGenerator;
import com.company.project.common.log.SystemLog;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
@RestController
@RequestMapping("/auth/role")
public class RoleController extends BaseController{
    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping("/add")
    @RequiresPermissions("role:add")
    public Result add(@RequestBody Role role) {
        roleService.save(role);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    @RequiresPermissions("role:delete")
    public Result delete(@RequestParam Integer id) {
        roleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @RequiresPermissions("role:update")
    public Result update(@RequestBody Role role) {
        roleService.update(role);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    @RequiresPermissions("role:list")
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = selectByPage(pageNum,pageSize,()->roleService.findAll());
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @SystemLog("角色管理-设置权限[新增]")
    @PostMapping("/addpermission")
    @RequiresPermissions("role:setpermission")
    public Result addPermission(@RequestBody RolePermission rolePermission) {
        rolePermissionService.saveRolePermission(rolePermission);
        return ResultGenerator.genSuccessResult();
    }

    @SystemLog("角色管理-设置权限[删除]")
    @PostMapping("/deletepermission")
    @RequiresPermissions("role:setpermissions")
    public Result deletePermission(@RequestBody RolePermission rolePermission) {
        rolePermissionService.deleteRolePermission(rolePermission);
        return ResultGenerator.genSuccessResult();
    }
}
