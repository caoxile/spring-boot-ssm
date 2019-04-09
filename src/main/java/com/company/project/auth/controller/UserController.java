package com.company.project.auth.controller;

import com.company.project.auth.model.User;
import com.company.project.auth.service.UserService;
import com.company.project.common.core.BaseController;
import com.company.project.common.core.QueryRequest;
import com.company.project.common.core.Result;
import com.company.project.common.core.ResultGenerator;
import com.company.project.common.log.SystemLog;
import com.github.pagehelper.PageInfo;
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

    @SystemLog("用户管理-新增用户")
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @SystemLog("用户管理-删除用户")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @SystemLog("用户管理-修改用户")
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @SystemLog("用户管理-查询用户列表")
    @PostMapping("/list")
    public Result list(@RequestParam QueryRequest request){
        PageInfo pageInfo = selectByPage(request,()->userService.findAll());
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
