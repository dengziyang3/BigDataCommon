package com.hzgc.common.service.journal.helper;

import com.hzgc.common.service.journal.dto.OperateLogDTO;
import com.hzgc.common.service.journal.service.OperateLogWriterService;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author qirongyu
 */
@Slf4j
public class OperateLogWriterHelper {

    private static OperateLogWriterService operateLogWriterService;

    public void setOperLogger(OperateLogWriterService operateLogWriterService) {
        OperateLogWriterHelper.operateLogWriterService = operateLogWriterService;
    }

    public static void success(OperateLogDTO log) {
        success(log.getServiceType(), log.getOperType(), log.getDescription());
    }

    public static void fail(OperateLogDTO log) {
        fail(log.getServiceType(), log.getOperType(), log.getDescription(), log.getFailureCause());
    }

    public static void success(int srvType, int operType, String operDesc) {
        writeLog(srvType, operType, operDesc, true, null);
    }

    public static void fail(int srvType, int operType, String operDesc, String failureCause) {
        writeLog(srvType, operType, operDesc, false, failureCause);
    }

    private static void writeLog(int srvType, int operType, String operDesc, boolean success, String failureCause) {
        OperateLogDTO operLog = new OperateLogDTO();
        operLog.setOperator(PrincipalHolder.getCurrentUsername());
        operLog.setOperType((byte) operType);
        operLog.setServiceType((byte) srvType);
        operLog.setDescription(operDesc);
        operLog.setSuccess(success);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        operLog.setOperTime(time);
        if (!success && null != failureCause) {
            operLog.setFailureCause(failureCause);
        }
        try {
            operateLogWriterService.writeLog(operLog);
        } catch (Exception e) {
            //写日志失败时，记录到本地的log日志中
            log.warn("Write operate log to local, {}", operLog.toString());
            log.error(e.getMessage());
        }
    }
}
