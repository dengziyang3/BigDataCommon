package com.hzgc.common.service.auth.scan;

import com.hzgc.common.service.auth.service.ResourceProviderSyncService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Set;

public class ResourceProviderAutoSyncRunner implements ApplicationRunner {

    private ResourceProviderSyncService resourceProviderSyncService;

    private ResourceProviderAnnotationScanner resourceProviderAnnotationScanner;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Set<ResourceProviderDefination> resourceProviderDefinations = resourceProviderAnnotationScanner.scan();
        if (resourceProviderDefinations != null && !resourceProviderDefinations.isEmpty()) {
            resourceProviderSyncService.sync(resourceProviderDefinations);
        }
    }

    public ResourceProviderSyncService getResourceProviderSyncService() {
        return resourceProviderSyncService;
    }

    public void setResourceProviderSyncService(ResourceProviderSyncService resourceProviderSyncService) {
        this.resourceProviderSyncService = resourceProviderSyncService;
    }

    public ResourceProviderAnnotationScanner getResourceProviderAnnotationScanner() {
        return resourceProviderAnnotationScanner;
    }

    public void setResourceProviderAnnotationScanner(ResourceProviderAnnotationScanner resourceProviderAnnotationScanner) {
        this.resourceProviderAnnotationScanner = resourceProviderAnnotationScanner;
    }
}
