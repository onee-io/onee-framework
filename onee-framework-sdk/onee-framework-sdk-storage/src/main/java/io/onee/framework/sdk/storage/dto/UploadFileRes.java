package io.onee.framework.sdk.storage.dto;

import lombok.Data;

/**
 * 文件上传结果
 *
 * @author onee
 * @since 1.1.0
 **/
@Data
public class UploadFileRes {

    /**
     * 文件唯一标识
     */
    private String key;

    /**
     * 文件网络访问地址
     */
    private String fileUrl;
}
