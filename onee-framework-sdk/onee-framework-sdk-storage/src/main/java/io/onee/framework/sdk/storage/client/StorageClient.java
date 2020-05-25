package io.onee.framework.sdk.storage.client;

import io.onee.framework.common.web.Result;
import io.onee.framework.sdk.storage.dto.UploadFileRes;

import java.io.File;
import java.io.InputStream;

/**
 * 云存储服务客户端定义
 *
 * @author onee
 * @since 1.1.0
 */
public interface StorageClient {

    /**
     * 文件上传
     *
     * @param key  文件唯一标识
     * @param file 文件对象
     */
    Result<UploadFileRes> upload(String key, File file);

    /**
     * 文件上传
     *
     * @param key      文件唯一标识
     * @param filePath 文件路径
     */
    Result<UploadFileRes> upload(String key, String filePath);

    /**
     * 文件上传
     *
     * @param key         文件唯一标识
     * @param inputStream 文件流
     */
    Result<UploadFileRes> upload(String key, InputStream inputStream);

    /**
     * 文件上传
     *
     * @param key       文件唯一标识
     * @param fileBytes 文件字节数组
     */
    Result<UploadFileRes> upload(String key, byte[] fileBytes);
}
