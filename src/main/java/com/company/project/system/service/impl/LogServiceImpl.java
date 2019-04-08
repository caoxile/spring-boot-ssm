package com.company.project.system.service.impl;

import com.company.project.common.core.BaseService;
import com.company.project.system.dao.LogMapper;
import com.company.project.system.model.Log;
import com.company.project.system.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @Author CodeGenerator
 * @Create 2019-04-08
 */
@Service
@Transactional
public class LogServiceImpl extends BaseService<Log> implements LogService {
    @Resource
    private LogMapper logMapper;

}
