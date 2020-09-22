# 日志组件使用指南

## 安装

仓库地址：[onee-framework-starter-log](https://search.maven.org/artifact/io.onee/onee-framework-starter-log)

### Maven

在 `pom.xml` 中添加以下依赖：

```xml
<dependency>
    <groupId>io.onee</groupId>
    <artifactId>onee-framework-starter-log</artifactId>
    <version>1.2.0-SNAPSHOT</version>
</dependency>
```

## 第一步：规范日志格式及存放路径

此步骤可通过日志框架的配置文件进行实现，下面以 logback 配置为例，统一日志格式及存放路径：

日志格式：`日期时间|日志级别|线程名|客户端ip|调用链id|类名.方法名[行号]|日志内容`

存放路径：`/opt/logs/${app_name}/${app_name}.${yyyy-MM-dd}.${i}.log`

配置文件样例：![logback-sample.xml](../onee-framework-log/src/main/resources/logback-sample.xml)


## 第二步：对日志进行脱敏

日志规范组件提供了 logback 的消息转换器，可以在 logback 的配置文件（logback-spring.xml）中添加以下配置，即可实现全局的日志脱敏。

```xml
<conversionRule conversionWord="msg" converterClass="io.onee.framework.log.convert.SensitiveConverter" />
```


## 第三步：对 http 请求进行跟踪并获取请求 IP

对 http 请求生成 traceId 以及获取请求 IP 是通过拦截器实现的，组件已经实现好拦截器的内容，只需要在项目中配置此拦截器即可（建议放在拦截器链的第一个）。

```java
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
 
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 添加http请求跟踪拦截器
        registry.addInterceptor(new TraceMvcInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
```

组件提供了 http 接口的日志切面，引入组件后可输出所有接口的请求路径、出入参、耗时、返回码等信息，无需单独配置，便于通过日志对接口请求情况进行统计；


## 第四步：对 dubbo 请求进行跟踪并获取请求 IP

对 dubbo 请求生成 traceId 以及获取请求 IP 是通过扩展 dubbo 过滤器实现的，组件已经实现好过滤器的内容，只需要在 dubbo 服务的提供方及消费方配置此拦截器即可。

```xml
<!-- alibaba dubbo 服务消费方/提供方配置拦截器 -->
<dubbo:consumer filter="traceAlibabaDubboFilter" />
<dubbo:provider filter="traceAlibabaDubboFilter" />
 
<!-- apache dubbo 服务消费方/提供方配置拦截器 -->
<dubbo:consumer filter="traceApacheDubboFilter" />
<dubbo:provider filter="traceApacheDubboFilter" />
```


## 结束

至此，项目日志改造基本完成，可以看到每一句日志均按规范输出，并且都包含 traceId 以及 clientIp，通过 traceId 可以关联串起一次请求所经过的所有服务的日志，可以极大提升通过日志查找问题的效率。
