package com.hzgc.common.service.error;

/**
 * 业务异常
 *
 * @author liuzk
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int errorCode = 0;

    public BusinessException(int errorCode, String... msgArgs) {
        super(RestErrorTranslator.get(errorCode, msgArgs));
        this.setErrorCode(errorCode);
    }

    public BusinessException(Throwable cause, int errorCode, String... msgArgs) {
        super(RestErrorTranslator.get(errorCode, msgArgs), cause);
        this.setErrorCode(errorCode);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
