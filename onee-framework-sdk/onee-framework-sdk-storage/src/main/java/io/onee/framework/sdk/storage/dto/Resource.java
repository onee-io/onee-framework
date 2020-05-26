package io.onee.framework.sdk.storage.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 资源数据
 *
 * @author onee
 * @since 1.1.0
 **/
@Data
@Builder
public class Resource {

    /**
     * 资源所属空间名称
     */
    private String bucketName;

    /**
     * 资源唯一标识
     */
    private String key;

    /**
     * 资源公网访问地址
     */
    private String publicUrl;

    /**
     * 资源私网访问地址
     */
    private String privateUrl;
}
