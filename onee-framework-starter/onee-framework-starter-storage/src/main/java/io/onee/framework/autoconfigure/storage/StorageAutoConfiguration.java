package io.onee.framework.autoconfigure.storage;

import io.onee.framework.sdk.storage.client.QiniuStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 存储组件自动装配类
 *
 * @author onee
 * @since 1.1.0
 **/
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration {

    @Autowired
    private StorageProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "of.storage.qiniu", value = {"access-key", "secret-key", "bucket"})
    QiniuStorageClient qiniuStorageClient() {
        return new QiniuStorageClient(properties.getQiniu().getAccessKey(), properties.getQiniu().getSecretKey(), properties.getQiniu().getBucket());
    }
}
