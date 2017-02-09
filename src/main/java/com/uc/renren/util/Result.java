package com.uc.renren.util;

/**
 * 
 * Result 
 * @author yangxinyan
 * @date 2016年7月1日 下午3:41:23
 *
 * @param <T>
 */
public class Result<T> {

    /**
     * 对外返回的对象
     */
    private T data;

    /**
     * 返回状态码
     */
    private int code = RES_STATUS.SERVER_UNKONW_ERROR.code;

    /**
     * 返回消息
     */
    private String msg = RES_STATUS.SERVER_UNKONW_ERROR.msg;

    public Result() {
        super();
    }

    public Result(RES_STATUS status) {
        super();
        this.code = status.code;
        this.msg = status.msg;
    }

    public Result(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Result(T data, int code, String msg) {
        super();
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public void setStatus(RES_STATUS status) {
        this.code = status.code;
        this.msg = status.msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    /**
     * 服务器unix utc时间戳秒值
     *
     * @return
     */
    public long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

}
