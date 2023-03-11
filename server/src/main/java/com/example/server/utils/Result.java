package com.example.server.utils;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 结果数据
     */
    private T data;

    public Result(){
    }
    public Result(Integer code,String message){
        this.code=code;
        this.message = message;
    }
    public Result(T data){
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }
    public Result (Integer code,String message,T data){
        this.code=code;
        this.data = data;
        this.message=message;
    }

    public static <T> Result<T> setCommonStatusAndData(ResultCode resultCode, T data){
        return new Result<>(resultCode.getCode(), resultCode.getMessage(),data);
    }

    public static Result setCommonStatusNoData(ResultCode resultCode){
        return new Result<>(resultCode.getCode(), resultCode.getMessage());
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static Result<?> failed() {
        return new Result<>(ResultCode.COMMON_FAILED.getCode(), ResultCode.COMMON_FAILED.getMessage(), null);
    }

    public static Result<?> failed(String message) {
        return new Result<>(ResultCode.COMMON_FAILED.getCode(), message, null);
    }

    public static Result<?> failed(Result errorResult) {
        return new Result<>(errorResult.getCode(), errorResult.getMessage(), null);
    }

    public static Result<?> failed(Integer code, String message) {
        return new Result<>(code, message, null);
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

