package com.company.project.system.controller;

import com.company.project.common.core.Result;
import com.company.project.common.core.ResultGenerator;
import com.company.project.system.service.SessionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/system/session")
public class SessionController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    SessionService sessionService;


    @PostMapping("/list")
    @RequiresPermissions("system:session-list")
    public Result list() {
        return ResultGenerator.genSuccessResult(sessionService.list());
    }

    @RequiresPermissions("system:session-kickout")
    @PostMapping("/kickout")
    public Result forceLogout(@RequestParam String sessionId) {
        sessionService.forceLogout(sessionId);
        return ResultGenerator.genSuccessResult();
    }
}
