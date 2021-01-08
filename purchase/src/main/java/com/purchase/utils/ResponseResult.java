package com.purchase.utils;

import com.purchase.enums.InternationalizationEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by ben on 2017-03-14 0014.
 * 移动端交互传递实体
 */
@ApiModel(value = "返回数据")
public class ResponseResult<T> implements Serializable {

    @ApiModelProperty(value = "请求状态码")
    private int statusCode;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public ResponseResult() {

    }
    /**
     * 返回成功
     * @param o
     * @return
     */
    public static ResponseResult successResult(Object o,HttpServletRequest request){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setStatusCode(ResponseCode.SUCCESS);
        responseResult.setMessage(InternationalizationEnum.SUCCESS.getMsg(request));
        responseResult.setData(o);
        return responseResult;
    }


    /**
     * 返回失败
     * @param err
     * @return
     */
    public static ResponseResult failResult(String err){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setStatusCode(ResponseCode.FAIL);
        responseResult.setMessage(err);
        return responseResult;
    }


    /**
     * 用户登陆登陆过期
     * @return
     */
    public static ResponseResult userKeyExpireResult(HttpServletRequest request){
        ResponseResult responseResult=new ResponseResult();
        responseResult.setStatusCode(ResponseCode.USER_KEY_EXPIRE);
        responseResult.setMessage(InternationalizationEnum.LOGIN_INVALID.getMsg(request));
        return responseResult;
    }


    public ResponseResult(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
