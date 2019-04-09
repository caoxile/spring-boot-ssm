package com.company.project.system.service;

import com.company.project.common.core.Service;
import com.company.project.system.model.Log;


/**
 * @Author CodeGenerator
 * @Create 2019-04-08
 */
public interface LogService extends Service<Log> {
    void saveLog(Log log);
}
