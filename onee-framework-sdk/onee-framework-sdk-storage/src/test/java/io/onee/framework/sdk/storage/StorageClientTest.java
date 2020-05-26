package io.onee.framework.sdk.storage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import io.onee.framework.sdk.storage.client.QiniuStorageClient;
import io.onee.framework.sdk.storage.client.StorageClient;
import io.onee.framework.sdk.storage.dto.Bucket;
import io.onee.framework.sdk.storage.dto.Region;
import io.onee.framework.sdk.storage.dto.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 云存储客户端单元测试
 *
 * @author onee
 * @since 1.1.0
 **/
public class StorageClientTest {

    /**
     * 七牛云存储测试
     */
    @Test
    public void testQiniuStorage() {
        String accessKey = "";
        String secretKey = "";
        String bucketName = IdUtil.simpleUUID();
        // 创建客户端
        StorageClient storageClient = new QiniuStorageClient(accessKey, secretKey, bucketName);
        // 获取所有空间
        List<Bucket> bucketList = storageClient.getAllBucket();
        int currentBucketCount = bucketList.size();
        // 创建空间
        Bucket bucket = storageClient.createBucket(bucketName, Region.HB);
        // 获取新创建的空间信息，并验证是否创建成功
        Bucket bucket1 = storageClient.getBucket(bucketName);
        Assert.assertNotNull(bucket1);
        Assert.assertEquals(bucket.getBucketName(), bucket1.getBucketName());
        List<Bucket> bucketList1 = storageClient.getAllBucket();
        int currentBucketCount1 = bucketList1.size();
        Assert.assertEquals(currentBucketCount + 1, currentBucketCount1);
        // 上传文件
        String key1 = IdUtil.simpleUUID();
        Resource resource1 = storageClient.uploadResource(key1, FileUtil.file("E:\\images\\prize-list1.png"));
        String key2 = IdUtil.simpleUUID();
        Resource resource2 = storageClient.uploadResource(key2, FileUtil.file("E:\\images\\prize-list2.png"));
        String key3 = IdUtil.simpleUUID();
        Resource resource3 = storageClient.uploadResource(key3, FileUtil.file("E:\\images\\prize-list3.png"));
        String key4 = IdUtil.simpleUUID();
        Resource resource4 = storageClient.uploadResource(key4, FileUtil.file("E:\\images\\prize-list4.png"));
        // 获取文件访问连接
        System.out.println("resource1 public url: " + resource1.getPublicUrl());
        System.out.println("resource2 public url: " + storageClient.getPublicUrl(key2));
        System.out.println("resource3 private url: " + storageClient.getPrivateUrl(key3));
        System.out.println("resource4 private url: " + storageClient.getPrivateUrl(key4, 60));
        // 删除资源
        boolean result1 = storageClient.deleteResource(resource1);
        boolean result2 = storageClient.deleteResource(resource2.getKey());
        boolean result3 = storageClient.deleteResource(resource3.getBucketName(), resource3.getKey());
        boolean result4 = storageClient.deleteResource(resource4.getKey() + "xxxxxx"); // 一个不存在的 key
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
        Assert.assertFalse(result4);
        result4 = storageClient.deleteResource(resource4);
        Assert.assertTrue(result4);
    }
}
