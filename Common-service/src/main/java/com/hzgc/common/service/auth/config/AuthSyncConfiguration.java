package com.hzgc.common.service.auth.config;

import com.hzgc.common.service.auth.scan.AuthorizeCodeAnnotationScanner;
import com.hzgc.common.service.auth.scan.AuthorizeCodeAutoSyncRunner;
import com.hzgc.common.service.auth.scan.ResourceProviderAnnotationScanner;
import com.hzgc.common.service.auth.scan.ResourceProviderAutoSyncRunner;
import com.hzgc.common.service.auth.service.AuthorizeSyncService;
import com.hzgc.common.service.auth.service.AuthorizeSyncServiceImpl;
import com.hzgc.common.service.auth.service.ResourceProviderSyncService;
import com.hzgc.common.service.auth.service.ResourceProviderSyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author liuzhikun
 * @date 2018/04/27
 */
@Configuration
@EnableConfigurationProperties(AuthSyncProperties.class)
@ConditionalOnProperty(prefix = AuthSyncProperties.AUTH_SYNC, name = "enable", havingValue = "true", matchIfMissing = true)
@Import(ResttemplateConfiguration.class)
public class AuthSyncConfiguration {
    @Autowired
    private AuthSyncProperties authSyncProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public AuthorizeCodeAnnotationScanner authorizeCodeAnnotationScanner() {
        AuthorizeCodeAnnotationScanner scanner = new AuthorizeCodeAnnotationScanner();
        scanner.setBasePackages(Arrays.asList(StringUtils.
                commaDelimitedListToStringArray(authSyncProperties.getBasePackages())));
        return scanner;
    }

    @Bean
    public AuthorizeCodeAutoSyncRunner authorizeCodeAutoSyncRunner() {
        AuthorizeCodeAutoSyncRunner syncRunner = new AuthorizeCodeAutoSyncRunner();
        syncRunner.setAuthorizeSyncService(authorizeSyncService());
        syncRunner.setScanner(authorizeCodeAnnotationScanner());
        return syncRunner;
    }

    @Bean
    public AuthorizeSyncService authorizeSyncService() {
        AuthorizeSyncServiceImpl authorizeSyncService = new AuthorizeSyncServiceImpl();
        authorizeSyncService.setAuthSyncURI(authSyncProperties.getAuthSyncURL());
        authorizeSyncService.setRestTemplate(restTemplate);
        return authorizeSyncService;
    }

    @Bean
    public ResourceProviderAnnotationScanner resourceProviderAnnotationScanner() {
        ResourceProviderAnnotationScanner scanner = new ResourceProviderAnnotationScanner();
        scanner.setBasePackages(Arrays.asList(StringUtils.
                commaDelimitedListToStringArray(authSyncProperties.getBasePackages())));
        return scanner;
    }

    @Bean
    public ResourceProviderAutoSyncRunner resourceProviderAutoSyncRunner() {
        ResourceProviderAutoSyncRunner syncRunner = new ResourceProviderAutoSyncRunner();
        syncRunner.setResourceProviderSyncService(resourceProviderSyncService());
        syncRunner.setResourceProviderAnnotationScanner(resourceProviderAnnotationScanner());
        return syncRunner;
    }

    @Bean
    public ResourceProviderSyncService resourceProviderSyncService() {
        ResourceProviderSyncServiceImpl resourceProviderSyncService = new ResourceProviderSyncServiceImpl();
        resourceProviderSyncService.setResourceSyncURI(authSyncProperties.getResourceSyncURL());
        resourceProviderSyncService.setRestTemplate(restTemplate);
        return resourceProviderSyncService;
    }
}
