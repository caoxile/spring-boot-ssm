package com.company.project.auth.controller;

import com.company.project.auth.model.User;
import com.company.project.auth.model.UserRole;
import com.company.project.auth.service.UserRoleService;
import com.company.project.auth.service.UserService;
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
 * @Create 2019-04-08
 */
@RestController
@RequestMapping("/auth/user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @SystemLog("用户管理-新增用户")
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.addUser(user);
        return ResultGenerator.genSuccessResult();
    }

    @SystemLog("用户管理-删除用户")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteUser(id);
        return ResultGenerator.genSuccessResult();
    }

    @SystemLog("用户管理-修改用户")
    @PostMapping("/update")
    public Result update(@RequestBody User user) throws Exception {
        userService.updateUser(user);
        return ResultGenerator.genSuccessResult();
    }

    @SystemLog("用户管理-查询用户列表")
    @PostMapping("/list")
    @RequiresPermissions("user:list")
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = selectByPage(pageNum,pageSize,()->userService.findUsersWithRoleName());
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @SystemLog("用户管理-设置角色[新增]")
    @PostMapping("/addrole")
    @RequiresPermissions("user:setrole")
    public Result addRole(@RequestBody UserRole userRole) {
        userRoleService.saveUserRole(userRole);
        return ResultGenerator.genSuccessResult();
    }

    @SystemLog("用户管理-设置角色[删除]")
    @PostMapping("/deleterole")
    @RequiresPermissions("user:setrole")
    public Result deleteRole(@RequestBody UserRole userRole) {
        userRoleService.deleteUserRole(userRole);
        return ResultGenerator.genSuccessResult();
    }
}
