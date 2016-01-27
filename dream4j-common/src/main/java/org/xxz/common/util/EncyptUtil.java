package org.xxz.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 * @author xxz
 */
public final class EncyptUtil {
    
    /**
     * md5加密
     * @param data
     * @return
     */
    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }
    
    

}
