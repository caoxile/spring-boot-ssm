package com.company.project.common.core;

import com.company.project.auth.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.function.Supplier;

public class BaseController {

    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected User getCurrentUser() {
        return (User) getSubject().getPrincipal();
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected PageInfo<?> selectByPage(int pageNum,int pageSize, Supplier<?> s) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        s.get();
        return new PageInfo<>(page.getResult());
    }
}
