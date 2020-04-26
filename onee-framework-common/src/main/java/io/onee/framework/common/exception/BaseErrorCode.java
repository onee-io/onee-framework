package io.onee.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常错误码
 * Created by onee
 * Date: 2020/4/26 17:23
 */
@Getter
@AllArgsConstructor
public enum BaseErrorCode implements IErrorCode {
    // 框架异常
    F0001("F0001", "框架异常"),

    // 业务异常
    B0001("B0001", "业务异常");

    private String code;    // 错误码
    private String message; // 错误信息
}
