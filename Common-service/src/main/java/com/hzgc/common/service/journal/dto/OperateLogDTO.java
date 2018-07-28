package com.hzgc.common.service.journal.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author liuzhikun
 * @date 2018/05/03
 */
@Data
@ToString
public class OperateLogDTO {
    private String operator = "";

    private String operTime = "";

    private String ipAddress = "";

    private byte serviceType;

    private byte operType;

    private String description = "";

    private boolean success = true;

    private String failureCause = "";
}
