package io.onee.framework.autoconfigure.log;

import io.onee.framework.log.mvc.AccessMvcAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日志组件自动装配类
 *
 * @author onee
 * @since 1.2.0
 */
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnWebApplication
@ConditionalOnClass(RequestMapping.class)
public class LogAutoConfiguration {

    /**
     * 接口请求日志切片
     */
    @Bean
    @ConditionalOnClass(ProceedingJoinPoint.class)
    @ConditionalOnMissingBean(AccessMvcAspect.class)
    public AccessMvcAspect accessMvcAspect() {
        return new AccessMvcAspect();
    }

}
