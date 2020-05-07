package io.onee.framework.sdk.juhe.bo.response;

import lombok.Data;

/**
 * IP地址
 * https://www.juhe.cn/docs/api/id/1
 * Created by onee
 * Date: 2020/5/6 23:26
 */
@Data
public class Juhe1Res {

    /**
     * 国家
     */
    private String Country;

    /**
     * 省份区域，部分可能为空
     */
    private String Province;

    /**
     * 城市，部分可能为空
     */
    private String City;

    /**
     * 运营商，部分可能为空
     */
    private String Isp;
}
