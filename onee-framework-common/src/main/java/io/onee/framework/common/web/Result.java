package io.onee.framework.common.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.onee.framework.common.exception.IErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * api接口标准返回格式
 *
 * @author onee
 * @since 1.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 私有初始化方法，只允许使用提供的静态方法初始化
     */
    private Result() {
    }

    private Result(IErrorCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    private Result(IErrorCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 成功返回（不带数据）
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.OK);
    }

    /**
     * 成功返回（带数据）
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.OK, data);
    }

    /**
     * 失败返回（不带数据）
     */
    public static <T> Result<T> failure(IErrorCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 失败返回（带数据）
     */
    public static <T> Result<T> failure(IErrorCode resultCode, T data) {
        return new Result<>(resultCode, data);
    }

    /**
     * 是否成功
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code.equals(ResultCode.OK.getCode());
    }
}
