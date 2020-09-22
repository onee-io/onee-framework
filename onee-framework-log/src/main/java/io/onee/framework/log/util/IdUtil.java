package io.onee.framework.log.util;

import java.util.UUID;

/**
 * ID 生成工具类
 *
 * @author onee
 * @since 1.2.0
 */
public class IdUtil {

    /**
     * 获取随机唯一 UUID，可用于日志跟踪 traceId
     *
     * @return 唯一 UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
