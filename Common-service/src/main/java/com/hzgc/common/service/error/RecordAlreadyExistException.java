package com.hzgc.common.service.error;

/**
 * 记录已经存在异常
 *
 * @author liuzhikun
 */
public class RecordAlreadyExistException extends BusinessException {

    public RecordAlreadyExistException() {
        super(RestErrorCode.RECORD_ALREADY_EXIST);
    }

    public RecordAlreadyExistException(String msg) {
        super(RestErrorCode.RECORD_ALREADY_EXIST, msg);
    }
}
