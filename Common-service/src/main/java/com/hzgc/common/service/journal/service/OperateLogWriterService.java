package com.hzgc.common.service.journal.service;

import com.hzgc.common.service.journal.dto.OperateLogDTO;

/**
 * @author liuzhikun
 * @date 2018/05/28
 */
public interface OperateLogWriterService {
    void writeLog(OperateLogDTO operateLogDTO);
}
