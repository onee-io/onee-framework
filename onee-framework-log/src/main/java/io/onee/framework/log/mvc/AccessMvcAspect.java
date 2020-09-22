package io.onee.framework.log.mvc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.onee.framework.log.model.vo.AccessLogVO;
import io.onee.framework.log.util.IpUtil;
import io.onee.framework.log.util.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口请求日志切片
 *
 * @author onee
 * @since 1.2.0
 */
@Aspect
public class AccessMvcAspect {

    @Pointcut("(@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.PatchMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)) && (@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 记录日志信息
        AccessLogVO accessLogVO = new AccessLogVO();
        accessLogVO.setClientHost(IpUtil.getClientIp(request));
        accessLogVO.setUri(request.getRequestURI());
        long beginTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
            accessLogVO.setCostTime(System.currentTimeMillis() - beginTime);
            accessLogVO.setRequestJson(getRequestJson(joinPoint));
            accessLogVO.setRequestSize(accessLogVO.getRequestJson().length());
            accessLogVO.setResponseJson(JSON.toJSONString(result));
            accessLogVO.setResponseSize(accessLogVO.getResponseJson().length());
        } catch (Exception e) {
            accessLogVO.setCostTime(System.currentTimeMillis() - beginTime);
            throw e;
        } finally {
            LogUtil.printSplunkLog(accessLogVO);
        }
        return result;
    }

    /**
     * 输出文件类以外请求参数
     */
    private String getRequestJson(ProceedingJoinPoint point) {
        List<Object> argList = Arrays.stream(point.getArgs())
                .filter(arg -> !(arg instanceof ServletRequest)
                        && !(arg instanceof ServletResponse)
                        && !(arg instanceof MultipartFile)
                        && !(arg instanceof MultipartFile[]))
                .collect(Collectors.toList());
        return JSONArray.toJSONString(argList);
    }

}
