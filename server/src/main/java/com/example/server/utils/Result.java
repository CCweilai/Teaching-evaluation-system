package com.example.server.utils;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /**
     * 状态信息
     */
    private Boolean success; //状态信息
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 结果数据
     */
    private T data;



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess(){return success;}

    public void setSuccess(Boolean success) {
        this.success = success;
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

