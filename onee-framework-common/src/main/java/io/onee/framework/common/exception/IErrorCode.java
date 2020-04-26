package io.onee.framework.common.exception;

/**
 * 错误码结构
 * Created by onee
 * Date: 2020/4/26 17:02
 */
public interface IErrorCode {

    /**
     * 错误码
     */
    String getCode();

    /**
     * 错误信息
     */
    String getMessage();
}
