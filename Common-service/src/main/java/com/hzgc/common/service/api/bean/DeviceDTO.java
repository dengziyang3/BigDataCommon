package com.hzgc.common.service.api.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceDTO implements Serializable {
    private String id;
    private Long platId;
    private String code;
    private String name;
    private Integer devType;
    private Integer extType;
    private Integer channelType;
    private Integer channelSubType;
    private Byte status;
    private String parent;
    private String departmentId;
    private String description;
    private String serial;
}
