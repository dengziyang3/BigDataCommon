package com.hzgc.common.service.error;

/**
 * 数据库操作异常
 *
 * @author liuzk
 */
public class DatabaseOperateException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public DatabaseOperateException() {
        super(RestErrorCode.DB_OPERATE_FAIL);
    }

    public DatabaseOperateException(String msg) {
        super(RestErrorCode.DB_OPERATE_FAIL, msg);
    }
}
