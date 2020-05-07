package io.onee.framework.sdk.juhe.bo.response;

import lombok.Data;

/**
 * 聚合数据接口统一返回体
 * Created by onee
 * Date: 2020/5/6 23:00
 */
@Data
public class JuheRes<T> {

    /**
     * 返回码，0 为成功
     */
    private Integer error_code;

    /**
     * 返回说明
     */
    private String reason;

    /**
     * 返回结果集
     */
    private T result;

    /**
     * 是否成功
     */
    public boolean isSuccess() {
        return this.error_code.equals(0);
    }
}
