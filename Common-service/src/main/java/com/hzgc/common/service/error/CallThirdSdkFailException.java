package com.hzgc.common.service.error;

/**
 * 调用第三方sdk失败异常
 *
 * @author liuzk
 */
public class CallThirdSdkFailException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public CallThirdSdkFailException() {
        super(RestErrorCode.CALL_THIRD_SDK);
    }

    public CallThirdSdkFailException(String msg) {
        super(RestErrorCode.CALL_THIRD_SDK, msg);
    }
}
