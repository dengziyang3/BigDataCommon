package com.hzgc.common.service.error;

/**
 * 无权限异常
 *
 * @author liuzk
 */
public class NoPermissionException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public NoPermissionException() {
        super(RestErrorCode.NO_PERMISSION);
    }

    public NoPermissionException(String msg) {
        super(RestErrorCode.NO_PERMISSION, msg);
    }
}
