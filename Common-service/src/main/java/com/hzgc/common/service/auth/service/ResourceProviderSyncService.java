package com.hzgc.common.service.auth.service;

import com.hzgc.common.service.auth.scan.ResourceProviderDefination;

import java.util.Set;

/**
 * 资源提供者注册
 *
 * @author wangdl
 */
public interface ResourceProviderSyncService {
    void sync(Set<ResourceProviderDefination> resourceProviderSyncServices);
}
