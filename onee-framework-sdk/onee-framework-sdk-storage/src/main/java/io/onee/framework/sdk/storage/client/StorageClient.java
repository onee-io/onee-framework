package io.onee.framework.sdk.storage.client;

import io.onee.framework.sdk.storage.dto.Bucket;
import io.onee.framework.sdk.storage.dto.Region;
import io.onee.framework.sdk.storage.dto.Resource;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 云存储服务客户端定义
 *
 * @author onee
 * @since 1.1.0
 */
public interface StorageClient {

    /**
     * 获取所有空间列表
     *
     * @return 空间列表
     */
    List<Bucket> getAllBucket();

    /**
     * 通过空间名称获取指定空间信息
     *
     * @param bucketName 空间名称
     * @return 空间数据
     */
    Bucket getBucket(String bucketName);

    /**
     * 获取空间域名
     *
     * @param bucketName 空间名称
     * @return 空间域名
     */
    String getDomainOfBucket(String bucketName);

    /**
     * 创建空间
     *
     * @param bucketName 空间名称
     * @param region     存储区域
     * @return 空间数据
     */
    Bucket createBucket(String bucketName, Region region);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param file 文件对象
     * @return 资源数据，默认生成 uuid 作为资源 key
     */
    Resource uploadResource(File file);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param filePath 文件路径
     * @return 资源数据，默认生成 uuid 作为资源 key
     */
    Resource uploadResource(String filePath);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param inputStream 文件流
     * @return 资源数据，默认生成 uuid 作为资源 key
     */
    Resource uploadResource(InputStream inputStream);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param fileBytes 文件字节数组
     * @return 资源数据，默认生成 uuid 作为资源 key
     */
    Resource uploadResource(byte[] fileBytes);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param key  文件唯一标识
     * @param file 文件对象
     * @return 资源数据
     */
    Resource uploadResource(String key, File file);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param key      文件唯一标识
     * @param filePath 文件路径
     * @return 资源数据
     */
    Resource uploadResource(String key, String filePath);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param key         文件唯一标识
     * @param inputStream 文件流
     * @return 资源数据
     */
    Resource uploadResource(String key, InputStream inputStream);

    /**
     * 资源上传到默认空间（初始化时指定）
     *
     * @param key       文件唯一标识
     * @param fileBytes 文件字节数组
     * @return 资源数据
     */
    Resource uploadResource(String key, byte[] fileBytes);

    /**
     * 资源上传到指定空间
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @param file       文件对象
     * @return 资源数据
     */
    Resource uploadResource(String bucketName, String key, File file);

    /**
     * 资源上传到指定空间
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @param filePath   文件路径
     * @return 资源数据
     */
    Resource uploadResource(String bucketName, String key, String filePath);

    /**
     * 资源上传到指定空间
     *
     * @param bucketName  空间名称
     * @param key         文件唯一标识
     * @param inputStream 文件流
     * @return 资源数据
     */
    Resource uploadResource(String bucketName, String key, InputStream inputStream);

    /**
     * 资源上传到指定空间
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @param fileBytes  文件字节数组
     * @return 资源数据
     */
    Resource uploadResource(String bucketName, String key, byte[] fileBytes);

    /**
     * 获取默认空间资源公网访问地址
     *
     * @param key 文件唯一标识
     * @return 资源公网访问地址
     */
    String getPublicUrl(String key);

    /**
     * 获取默认空间资源私网访问地址，默认3600秒过期
     *
     * @param key 文件唯一标识
     * @return 资源私网访问地址
     */
    String getPrivateUrl(String key);

    /**
     * 获取默认空间资源私网访问地址
     *
     * @param key    文件唯一标识
     * @param expire 过期时间，单位秒
     * @return 资源私网访问地址
     */
    String getPrivateUrl(String key, long expire);

    /**
     * 获取指定空间资源公网访问地址
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @return 资源公网访问地址
     */
    String getPublicUrl(String bucketName, String key);

    /**
     * 获取指定空间资源私网访问地址，默认3600秒过期
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @return 资源私网访问地址
     */
    String getPrivateUrl(String bucketName, String key);

    /**
     * 获取指定空间资源私网访问地址
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @param expire     过期时间，单位秒
     * @return 资源私网访问地址
     */
    String getPrivateUrl(String bucketName, String key, long expire);

    /**
     * 删除指定资源，如未指定 bucketName，则删除默认空间中指定资源
     *
     * @param resource 资源数据
     */
    boolean deleteResource(Resource resource);

    /**
     * 删除默认空间中指定资源
     *
     * @param key 文件唯一标识
     * @return 操作结果，成功返回 true，失败返回 false
     */
    boolean deleteResource(String key);

    /**
     * 删除指定空间中指定资源
     *
     * @param bucketName 空间名称
     * @param key        文件唯一标识
     * @return 操作结果，成功返回 true，失败返回 false
     */
    boolean deleteResource(String bucketName, String key);
}
