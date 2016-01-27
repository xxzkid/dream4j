package org.xxz.common.util;

import java.util.UUID;

/**
 * id 生成工具类
 * @author xxz
 */
public final class IDUtil {
    
    /**
     * 生成uuid
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
