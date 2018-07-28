package com.hzgc.common.service.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liuzhikun
 * @date 2018/04/27
 */
@Data
@ConfigurationProperties(prefix = AuthSyncProperties.AUTH_SYNC)
public class AuthSyncProperties {
    final static String AUTH_SYNC = "auth.sync";

    private String basePackages = "com.hzgc.service";
    private String authSyncURL = "http://auth-center/permission/sync";
    private String resourceSyncURL = "http://auth-center/resource/sync";
}
