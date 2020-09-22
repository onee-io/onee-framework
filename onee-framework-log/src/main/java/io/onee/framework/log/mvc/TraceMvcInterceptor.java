package io.onee.framework.log.mvc;

import io.onee.framework.log.util.IdUtil;
import io.onee.framework.log.util.IpUtil;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * MVC 日志跟踪拦截器
 * 适用于 web mvc的程序，配置后会自动提取请求者的真实 ip、生成 traceId，放入到 org.slf4j.MDC中
 * 注：如果客户端 header中有 “of-trace-id”，traceId会延续下去
 *
 * @author onee
 * @since 1.2.0
 */
public class TraceMvcInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 提取请求头中的 traceId 或自生成
        String traceId = request.getHeader("of-trace-id");
        if (traceId == null || traceId.length() == 0) {
            traceId = IdUtil.getUUID();
        }
        MDC.put("traceId", traceId);
        MDC.put("clientIp", IpUtil.getClientIp(request));
        MDC.put("uri", request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 兼容老版本 spring-webmvc 实现
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove("traceId");
        MDC.remove("clientIp");
        MDC.remove("uri");
    }
}
