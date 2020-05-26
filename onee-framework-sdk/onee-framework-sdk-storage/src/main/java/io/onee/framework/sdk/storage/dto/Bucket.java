package io.onee.framework.sdk.storage.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 空间数据
 *
 * @author onee
 * @since 1.1.0
 **/
@Data
@Builder
public class Bucket {

    /**
     * 空间名称
     */
    private String BucketName;

    /**
     * 空间域名
     */
    private String domainOfBucket;
}
