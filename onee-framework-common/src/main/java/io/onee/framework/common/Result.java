package io.onee.framework.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口标准返回格式
 * Created by onee
 * Date: 2020/4/26 14:52
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;    // 返回码
    private String message; // 返回信息
    private T data;         // 数据

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getDesc();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getDesc();
        this.data = data;
    }

    public static Result success() {
        return new Result(ResultCode.OK);
    }

    public static <T extends Serializable> Result<T> success(T data) {
        return new Result<T>(ResultCode.OK, data);
    }

    public static Result failure(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public static <T extends Serializable> Result<T> failure(ResultCode resultCode, T data) {
        return new Result<T>(resultCode, data);
    }

    /**
     * 是否成功
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code.equals(ResultCode.OK.getCode());
    }
}
