package io.onee.framework.sdk.storage.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import io.onee.framework.common.web.Result;
import io.onee.framework.common.web.ResultCode;
import io.onee.framework.sdk.storage.dto.UploadFileRes;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;

/**
 * 七牛云存储实现
 *
 * @author onee
 * @since 1.1.0
 **/
@Slf4j
public class QiniuStorageClient implements StorageClient {

    /**
     * 域名
     */
    private final String domain;

    /**
     * 空间名称
     */
    private final String bucket;

    /**
     * 服务认证
     */
    private final Auth auth;

    /**
     * 上传客户端
     */
    private final UploadManager uploadManager;

    public QiniuStorageClient(String accessKey, String secretKey, String domain, String bucket) {
        this.auth = Auth.create(accessKey, secretKey);
        Configuration configuration = new Configuration(Region.autoRegion());
        this.uploadManager = new UploadManager(configuration);
        this.domain = domain;
        this.bucket = bucket;
    }

    @Override
    public Result<UploadFileRes> upload(String key, File file) {
        return upload(key, FileUtil.readBytes(file));
    }

    @Override
    public Result<UploadFileRes> upload(String key, String filePath) {
        return upload(key, FileUtil.readBytes(filePath));
    }

    @Override
    public Result<UploadFileRes> upload(String key, InputStream inputStream) {
        return upload(key, IoUtil.readBytes(inputStream));
    }

    @Override
    public Result<UploadFileRes> upload(String key, byte[] fileBytes) {
        try {
            Response response = uploadManager.put(fileBytes, getUnionKey(key), auth.uploadToken(bucket));
            log.info(JSON.toJSONString(response));
            return response.isOK() ? Result.success(getUploadFileRes(key)) : Result.failure(ResultCode.C0001);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.failure(ResultCode.C0001);
        }
    }

    /**
     * 生成文件唯一标识
     *
     * @param key 文件唯一标识
     * @return 外部传入则使用外部值，否则生成 uuid 唯一标识
     */
    String getUnionKey(String key) {
        return StrUtil.isNotBlank(key) ? key : IdUtil.simpleUUID();
    }

    /**
     * 上传文件成功 生成返回结果
     *
     * @param key 文件唯一标识
     * @return 上传结果
     */
    UploadFileRes getUploadFileRes(String key) {
        UploadFileRes res = new UploadFileRes();
        res.setKey(key);
        if (!domain.startsWith("http://") && !domain.startsWith("https://")) {
            res.setFileUrl("http://" + domain + "/" + key);
        } else {
            res.setFileUrl(domain + "/" + key);
        }
        return res;
    }
}
