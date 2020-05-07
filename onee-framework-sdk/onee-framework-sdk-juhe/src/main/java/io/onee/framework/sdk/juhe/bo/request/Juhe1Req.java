package io.onee.framework.sdk.juhe.bo.request;

import lombok.Data;

/**
 * IP地址
 * https://www.juhe.cn/docs/api/id/1
 * Created by onee
 * Date: 2020/5/6 23:23
 */
@Data
public class Juhe1Req {

    /**
     * 需要查询的IP地址
     */
    private String ip;
}
