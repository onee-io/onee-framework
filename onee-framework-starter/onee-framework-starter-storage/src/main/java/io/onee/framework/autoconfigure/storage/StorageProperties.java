package io.onee.framework.autoconfigure.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 存储组件属性配置类
 *
 * @author onee
 * @since 1.1.0
 **/
@Data
@ConfigurationProperties("of.storage")
public class StorageProperties {

    /**
     * 七牛云服务配置
     */
    private Qiniu qiniu;

    /**
     * 七牛云服务配置
     */
    @Data
    public static class Qiniu {

        /**
         * 访问密钥
         */
        private String accessKey;

        /**
         * 加密密钥
         */
        private String secretKey;

        /**
         * 空间名称
         */
        private String bucket;
    }
}
