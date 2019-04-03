package com.company.project.common.util;

/**
 * @Package com.company.project.common.util
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-07-24
 */
public class StringUtil {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str.trim()) ;
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
}
