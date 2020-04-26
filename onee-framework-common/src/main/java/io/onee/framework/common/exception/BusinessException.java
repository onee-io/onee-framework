package io.onee.framework.common.exception;

/**
 * 业务异常类
 * Created by onee
 * Date: 2020/4/26 17:32
 */
public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(message);
        this.setErrorCode(BaseErrorCode.B0001);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.setErrorCode(BaseErrorCode.B0001);
    }

    public BusinessException(IErrorCode errorCode) {
        super(errorCode);
    }
}
