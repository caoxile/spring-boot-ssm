package com.company.project.auth.controller;

import com.company.project.auth.model.Permission;
import com.company.project.auth.service.PermissionService;
import com.company.project.common.core.BaseController;
import com.company.project.common.core.Result;
import com.company.project.common.core.ResultGenerator;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author CodeGenerator
 * @Create 2019-04-11
 */
@RestController
@RequestMapping("/auth/permission")
public class PermissionController extends BaseController{
    @Resource
    private PermissionService permissionService;

    @PostMapping("/add")
    @RequiresPermissions("permission:add")
    public Result add(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    @RequiresPermissions("permission:delete")
    public Result delete(@RequestParam Integer id) {
        permissionService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @RequiresPermissions("permission:update")
    public Result update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    @RequiresPermissions("permission:list")
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = selectByPage(pageNum,pageSize,()->permissionService.findAll());
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
