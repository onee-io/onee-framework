package io.onee.framework.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 示例工程启动类
 *
 * @author onee
 * @since 1.1.0
 **/
@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {
        // tips: 请先修改 application.properties 中相关属性配置，否则启动会报错
        SpringApplication.run(Bootstrap.class, args);
    }
}
