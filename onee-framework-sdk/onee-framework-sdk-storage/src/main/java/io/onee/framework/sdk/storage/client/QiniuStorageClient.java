package io.onee.framework.sdk.storage.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import io.onee.framework.sdk.storage.dto.Bucket;
import io.onee.framework.sdk.storage.dto.Region;
import io.onee.framework.sdk.storage.dto.Resource;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 七牛云存储实现
 *
 * @author onee
 * @since 1.1.0
 **/
@Slf4j
public class QiniuStorageClient implements StorageClient {

    /**
     * 默认空间名称，可在控制台 “对象存储” - “空间管理” - “新建空间” 或调用 createBucket 方法创建
     */
    private final String defaultBucketName;

    /**
     * 七牛服务认证，accessKey、secretKey 请在 “个人中心” - “密钥管理” 中获取
     */
    private final Auth auth;

    /**
     * 空间操作客户端
     */
    private final BucketManager bucketManager;

    /**
     * 资源上传客户端
     */
    private final UploadManager uploadManager;

    /**
     * 初始化七牛云存储客户端
     *
     * @param accessKey 访问密钥
     * @param secretKey 加密密钥
     */
    public QiniuStorageClient(String accessKey, String secretKey, String bucket) {
        // 授权认证
        this.auth = Auth.create(accessKey, secretKey);
        // 连接配置，暂不做定制化配置，均使用默认值
        Configuration configuration = new Configuration();
        this.bucketManager = new BucketManager(this.auth, configuration);
        this.uploadManager = new UploadManager(configuration);
        this.defaultBucketName = bucket;
        log.info("qiniu storage client is initialized.");
    }

    @Override
    public List<Bucket> getAllBucket() {
        List<Bucket> bucketList = new ArrayList<>();
        try {
            String[] buckets = bucketManager.buckets();
            for (String bucketName : buckets) {
                bucketList.add(getBucket(bucketName));
            }
        } catch (Exception e) {
            log.error("七牛云-获取空间列表异常, errMsg={}", e.getMessage());
        }
        return bucketList;
    }

    @Override
    public Bucket getBucket(String bucketName) {
        Assert.notBlank(bucketName);
        String domainOfBucket = getDomainOfBucket(bucketName);
        return domainOfBucket != null ? Bucket.builder().BucketName(bucketName).domainOfBucket(domainOfBucket).build() : null;
    }

    @Override
    public String getDomainOfBucket(String bucketName) {
        Assert.notBlank(bucketName);
        try {
            String[] domains = bucketManager.domainList(bucketName);
            return domains.length > 0 ? domains[domains.length - 1] : null;
        } catch (Exception e) {
            log.error("七牛云-获取空间域名异常, bucketName={}, errMsg={}", bucketName, e.getMessage());
            return null;
        }
    }

    @Override
    public Bucket createBucket(String bucketName, Region region) {
        try {
            Response response = bucketManager.createBucket(bucketName, region.getQnRegion());
            if (response.isOK()) {
                return getBucket(bucketName);
            } else {
                log.error("七牛云-创建空间失败, bucketName={}, errMsg={}", bucketName, response.error);
            }
        } catch (Exception e) {
            log.error("七牛云-创建空间异常, bucketName={}, errMsg={}", bucketName, e.getMessage());
        }
        return null;
    }

    @Override
    public Resource uploadResource(File file) {
        return uploadResource(getUnionKey(null), file);
    }

    @Override
    public Resource uploadResource(String filePath) {
        return uploadResource(getUnionKey(null), filePath);
    }

    @Override
    public Resource uploadResource(InputStream inputStream) {
        return uploadResource(getUnionKey(null), inputStream);
    }

    @Override
    public Resource uploadResource(byte[] fileBytes) {
        return uploadResource(getUnionKey(null), fileBytes);
    }

    @Override
    public Resource uploadResource(String key, File file) {
        return uploadResource(defaultBucketName, key, file);
    }

    @Override
    public Resource uploadResource(String key, String filePath) {
        return uploadResource(defaultBucketName, key, filePath);
    }

    @Override
    public Resource uploadResource(String key, InputStream inputStream) {
        return uploadResource(defaultBucketName, key, inputStream);
    }

    @Override
    public Resource uploadResource(String key, byte[] fileBytes) {
        return uploadResource(defaultBucketName, key, fileBytes);
    }

    @Override
    public Resource uploadResource(String bucketName, String key, File file) {
        return uploadByBytes(bucketName, key, FileUtil.readBytes(file));
    }

    @Override
    public Resource uploadResource(String bucketName, String key, String filePath) {
        return uploadByBytes(bucketName, key, FileUtil.readBytes(filePath));
    }

    @Override
    public Resource uploadResource(String bucketName, String key, InputStream inputStream) {
        return uploadByBytes(bucketName, key, IoUtil.readBytes(inputStream));
    }

    @Override
    public Resource uploadResource(String bucketName, String key, byte[] fileBytes) {
        return uploadByBytes(bucketName, key, fileBytes);
    }

    @Override
    public String getPublicUrl(String key) {
        return getPublicUrl(defaultBucketName, key);
    }

    @Override
    public String getPrivateUrl(String key) {
        return getPrivateUrl(defaultBucketName, key);
    }

    @Override
    public String getPrivateUrl(String key, long expire) {
        return getPrivateUrl(defaultBucketName, key, expire);
    }

    @Override
    public String getPublicUrl(String bucketName, String key) {
        Assert.notBlank(key);
        String domainOfBucket = getDomainOfBucket(bucketName);
        if (domainOfBucket.startsWith("http://") || domainOfBucket.startsWith("https://")) {
            return String.format("%s/%s", domainOfBucket, key);
        } else {
            return String.format("http://%s/%s", domainOfBucket, key);
        }
    }

    @Override
    public String getPrivateUrl(String bucketName, String key) {
        return getPrivateUrl(bucketName, key, 3600);
    }

    @Override
    public String getPrivateUrl(String bucketName, String key, long expire) {
        Assert.notBlank(key);
        return auth.privateDownloadUrl(getPublicUrl(bucketName, key), expire);
    }

    @Override
    public boolean deleteResource(Resource resource) {
        return deleteResource(getBucketName(resource.getBucketName()), resource.getKey());
    }

    @Override
    public boolean deleteResource(String key) {
        return deleteResource(defaultBucketName, key);
    }

    @Override
    public boolean deleteResource(String bucketName, String key) {
        try {
            return bucketManager.delete(bucketName, key).isOK();
        } catch (Exception e) {
            log.error("七牛云-删除资源异常, bucketName={}, key={}, errMsg={}", bucketName, key, e.getMessage());
            return false;
        }
    }

    /**
     * 通过字节数组方式上传文件资源
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @param fileBytes  文件字节数组
     * @return 资源数据
     */
    public Resource uploadByBytes(String bucketName, String key, byte[] fileBytes) {
        try {
            Response response = uploadManager.put(fileBytes, getUnionKey(key), auth.uploadToken(bucketName));
            if (response.isOK()) {
                return Resource.builder()
                        .bucketName(bucketName)
                        .key(key)
                        .publicUrl(getPublicUrl(bucketName, key))
                        .privateUrl(getPrivateUrl(bucketName, key))
                        .build();
            } else {
                log.error("七牛云-上传文件失败, key={}, errMsg={}", key, response.error);
            }
        } catch (Exception e) {
            log.error("七牛云-上传文件异常, key={}, errMsg={}", key, e.getMessage());
        }
        return null;
    }

    /**
     * 获取空间名称
     *
     * @param bucketName 空间名称
     * @return 优先使用外部传入名称，否则使用默认空间名称
     */
    String getBucketName(String bucketName) {
        return StrUtil.isBlank(bucketName) ? defaultBucketName : bucketName;
    }

    /**
     * 生成文件唯一标识
     *
     * @param key 文件唯一标识
     * @return 优先使用外部传入标识，否则生成 uuid 唯一标识
     */
    String getUnionKey(String key) {
        return StrUtil.isNotBlank(key) ? key : IdUtil.simpleUUID();
    }
}
