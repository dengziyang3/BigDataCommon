package com.hzgc.common.service.auth.scan;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 定义资源提供者
 *
 * @author wangdl
 */
@Data
@ToString
@EqualsAndHashCode(of = {"resourceUri"})
public class ResourceProviderDefination {
    private String name;
    private String resourceUri;
    private String resourceType;
}
