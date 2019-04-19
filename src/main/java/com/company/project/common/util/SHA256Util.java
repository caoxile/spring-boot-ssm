package com.company.project.common.util;

import com.company.project.common.core.ServiceException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class SHA256Util {

    private static final Logger log = LoggerFactory.getLogger(SHA256Util.class);

    private final static String ALGORITHM = "SHA-256";

    public static String encrypt(String text) {

        if (null == text || text.equals("")){
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            return Hex.encodeHexString(hash);
        }catch (Exception e){
            throw new ServiceException("加密失败");
        }
    }

}
