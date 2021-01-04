package com.ywxs.blog.common.util;

import cn.hutool.http.HttpStatus;
import lombok.Data;

@Data
public class Result {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private Object data;// 返回数据

    public Result() {
        super();
    }


    public static Result ok(){
        return new Result(true, HttpStatus.HTTP_OK,"操作成功");
    }

    public static Result ok(Object data){
        return new Result(true,HttpStatus.HTTP_OK,"操作成功",data);
    }

    public static Result error(int code){
        return new Result(false,code,"操作失败");
    }
    public static Result error(String message, Object data){
        return new Result(false,HttpStatus.HTTP_INTERNAL_ERROR,message,data);
    }
    public static Result error(int code, String message){
        return new Result(false,code,message);
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
