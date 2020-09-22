package io.onee.framework.common.exception;

/**
 * 错误码结构
 *
 * @author onee
 * @since 1.0.0
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
