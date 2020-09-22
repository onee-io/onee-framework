package io.onee.framework.log.util;

import javax.servlet.http.HttpServletRequest;

/**
 * IP 工具类
 *
 * @author onee
 * @since 1.2.0
 */
public class IpUtil {

    /**
     * 获取客户端真实请求 IP
     *
     * @param request 客户端请求
     * @return 真实请求 IP
     */
    public static String getClientIp(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
