package com.hzgc.common.service.error;

/**
 * 数据库记录不存在异常
 *
 * @author liuzk
 */
public class RecordNotExistException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public RecordNotExistException() {
        super(RestErrorCode.RECORD_NOT_EXIST);
    }

    public RecordNotExistException(String msg) {
        super(RestErrorCode.RECORD_NOT_EXIST, msg);
    }

    public RecordNotExistException(String msg, Throwable cause) {
        super(cause, RestErrorCode.RECORD_NOT_EXIST, msg);
    }
}
