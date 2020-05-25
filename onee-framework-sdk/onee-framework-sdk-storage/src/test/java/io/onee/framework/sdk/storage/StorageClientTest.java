package io.onee.framework.sdk.storage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import io.onee.framework.common.web.Result;
import io.onee.framework.sdk.storage.client.QiniuStorageClient;
import io.onee.framework.sdk.storage.client.StorageClient;
import io.onee.framework.sdk.storage.dto.UploadFileRes;
import org.junit.Test;

/**
 * 云存储客户端单元测试
 *
 * @author onee
 * @since 1.1.0
 **/
public class StorageClientTest {

    /**
     * 七牛云
     */
    @Test
    public void testQiniuStorage() {
        String accessKey = "Ao9_ICi8oEy4qHA27HmMhS4sdPJPMIsIa61Yb5dA";
        String secretKey = "4xDB5chzq5TxSyxWcgsukO_XG719AL_Jgp6tH45P";
        String domain = "qiniu.onee.top";
        String bucket = "onee";
        StorageClient storageClient = new QiniuStorageClient(accessKey, secretKey, domain, bucket);
        Result<UploadFileRes> upload1 = storageClient.upload(IdUtil.simpleUUID(), FileUtil.file("E:\\images\\prize-list1.png"));
        System.out.println(JSON.toJSONString(upload1));
        Result<UploadFileRes> upload2 = storageClient.upload(IdUtil.simpleUUID(), "E:\\images\\prize-list2.png");
        System.out.println(JSON.toJSONString(upload2));
        Result<UploadFileRes> upload3 = storageClient.upload(IdUtil.simpleUUID(), FileUtil.getInputStream("E:\\images\\prize-list3.png"));
        System.out.println(JSON.toJSONString(upload3));
        Result<UploadFileRes> upload4 = storageClient.upload(IdUtil.simpleUUID(), FileUtil.readBytes("E:\\images\\prize-list4.png"));
        System.out.println(JSON.toJSONString(upload4));
    }
}
