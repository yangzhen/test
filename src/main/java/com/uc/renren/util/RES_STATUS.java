package com.uc.renren.util;

/**
 * RES_STATUS
 *
 * @author Wang Zhao
 * @date 2015年11月23日 下午4:38:40
 */
public enum RES_STATUS {

    SUCCESS(0, "操作成功"),

    SERVER_UNKONW_ERROR(500, "服务器开小差了,请稍后再试"),

    BAD_PARAM(400, "bad param"),

    ;

    RES_STATUS(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public final int code;
    public final String msg;

    public static RES_STATUS findStatusByCode(int code) {
        for (RES_STATUS status : RES_STATUS.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }

    /**
     * success:Y
     * not success:N
     *
     * @param code
     * @return
     */
    public static String isSuccess(int code) {
        if (code == RES_STATUS.SUCCESS.code) {
            return Constant.METHOD_SUCCESS;
        } else {
            return Constant.METHOD_FAIL;
        }
    }

}
