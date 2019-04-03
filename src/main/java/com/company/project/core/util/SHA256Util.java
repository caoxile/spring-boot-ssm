package com.company.project.core.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @version V1.0
 * @Title SHA256Encryptor
 * @Package com.qslb.framework.encrypt
 * @Description SHA256加密器
 * @Project qslb-framework
 * @Author yiminggang
 * @date 2016/9/1
 */
public class SHA256Util {

    private static final Logger log = LoggerFactory.getLogger(SHA256Util.class);

    private final static String ALGORITHM = "SHA-256";

    public static String encrypt(String text) throws Exception{

        if (null == text || text.equals("")){
            return "";
        }
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        byte[] hash = digest.digest(text.getBytes("UTF-8"));
        return Hex.encodeHexString(hash);
    }

}
