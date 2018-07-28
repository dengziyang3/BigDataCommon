package com.hzgc.common.service.auth.service;


import com.hzgc.common.service.auth.scan.DefaultAuthorizeDefination;

import java.util.Set;

/**
 * 权限颗粒同步服务
 *
 * @author liuzhikun
 */
public interface AuthorizeSyncService {
    void sync(Set<DefaultAuthorizeDefination> authorizeDefinations);
}
