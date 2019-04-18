package com.company.project.system.controller;

import com.company.project.common.core.BaseController;
import com.company.project.common.core.Result;
import com.company.project.common.core.ResultGenerator;
import com.company.project.system.model.Log;
import com.company.project.system.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author CodeGenerator
 * @Create 2019-04-08
 */
@RestController
@RequestMapping("/system/log")
public class LogController extends BaseController{
    @Resource
    private LogService logService;

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo pageInfo = selectByPage(pageNum,pageSize,()->logService.findAll());
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
