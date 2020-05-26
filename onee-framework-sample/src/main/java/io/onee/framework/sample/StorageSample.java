package io.onee.framework.sample;

import io.onee.framework.sdk.storage.client.StorageClient;
import io.onee.framework.sdk.storage.dto.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 云存储组件使用示例
 *
 * @author onee
 * @since 1.1.0
 **/
@Slf4j
@Service
public class StorageSample {

    @Autowired
    private StorageClient qiniuStorageClient;

    public void qiniuOperate() {
        // tips: 请先修改 application.properties 中 of.storage.qiniu.* 相关配置
        String filePath ="E:\\images\\asd.jpg";
        Resource resource = qiniuStorageClient.uploadResource(filePath);
        log.info("public url: {}", resource.getPublicUrl());
        boolean result = qiniuStorageClient.deleteResource(resource);
        log.info("delete result: {}", result);
    }
}
