package com.hzgc.common.service.error;

/**
 * rest错误码
 *
 * @author liuzhikun
 */
public interface RestErrorCode {

    /**
     * 成功
     */
    int OK = 0;
    /**
     * 未知错误
     */
    int UNKNOWN = 1;
    /**
     * 系统内部错误
     */
    int INTERNAL_SERVER = 2;
    /**
     * 非法参数错误
     */
    int ILLEGAL_ARGUMENT = 3;
    /**
     * 数据库记录不存在
     */
    int RECORD_NOT_EXIST = 4;
    /**
     * 数据库操作失败
     */
    int DB_OPERATE_FAIL = 5;
    /**
     * 未经认证的用户
     */
    int UNAUTHORIZED_USER = 6;
    /**
     * 调用本地sdk失败
     */
    int CALL_THIRD_SDK = 7;
    /**
     * 数据完整性异常(delete)
     */
    int DATA_INTEGRITY_VIOLATION_4_DELETE = 9;
    /**
     * 数据完整性异常(update)
     */
    int DATA_INTEGRITY_VIOLATION_4_UPDATE = 10;
    /**
     * RPC远程调用失败
     */
    int RPC_EXCEPTION = 11;
    /**
     * 数据库数据重复
     */
    int DB_DUPLICAET_KEY = 12;
    /**
     * RPC远程超时
     */
    int RPC_TIMEOUT = 13;
    /**
     * 无权限
     */
    int NO_PERMISSION = 14;
    /**
     * 数据库记录已经存在
     */
    int RECORD_ALREADY_EXIST = 15;
    /**
     * 设备已经配置了识别规则
     */
    int ERR_DEVICE_ALREADY_BIND_RULE = 2200;
}
