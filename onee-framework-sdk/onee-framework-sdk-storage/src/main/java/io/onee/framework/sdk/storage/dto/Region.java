package io.onee.framework.sdk.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 存储区域
 */
@Getter
@AllArgsConstructor
public enum Region {

    HD("华东", "z0"),
    HB("华北", "z1"),
    HN("华南", "z2"),
    BM("北美", "na0"),
    DNY("东南亚", "as0");

    /**
     * 区域说明
     */
    private final String desc;

    /**
     * 七牛云服务区域代码
     *
     * @see com.qiniu.storage.Region
     */
    private final String qnRegion;
}
