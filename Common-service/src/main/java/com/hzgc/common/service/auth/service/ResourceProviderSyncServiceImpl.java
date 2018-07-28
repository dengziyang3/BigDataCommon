package com.hzgc.common.service.auth.service;

import com.hzgc.common.service.auth.scan.ResourceProviderDefination;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

public class ResourceProviderSyncServiceImpl implements ResourceProviderSyncService {
    private String resourceSyncURI;

    private RestTemplate restTemplate;

    public void setResourceSyncURI(String resourceSyncURI) {
        this.resourceSyncURI = resourceSyncURI;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    @Override
    public void sync(Set<ResourceProviderDefination> resourceProviderSyncServices) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Set<ResourceProviderDefination>> entity =
                new HttpEntity<>(resourceProviderSyncServices, headers);
        restTemplate.postForEntity(resourceSyncURI, entity, Void.class);
    }

}
