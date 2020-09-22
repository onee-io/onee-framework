package io.onee.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常错误码
 *
 * @author onee
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum BaseErrorCode implements IErrorCode {

    // 框架异常
    F0001("F0001", "框架异常"),

    // 业务异常
    B0001("B0001", "业务异常");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;
}
