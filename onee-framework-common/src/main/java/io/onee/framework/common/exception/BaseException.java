package io.onee.framework.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常类
 * Created by onee
 * Date: 2020/4/26 17:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseException extends RuntimeException {

    private IErrorCode errorCode;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}


