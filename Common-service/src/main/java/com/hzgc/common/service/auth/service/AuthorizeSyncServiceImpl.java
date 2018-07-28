package com.hzgc.common.service.auth.service;

import com.hzgc.common.service.auth.scan.DefaultAuthorizeDefination;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * @author liuzhikun
 * @date 2018/04/27
 */
public class AuthorizeSyncServiceImpl implements AuthorizeSyncService {
    private String authSyncURI;

    private RestTemplate restTemplate;

    public void setAuthSyncURI(String authSyncURI) {
        this.authSyncURI = authSyncURI;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    @Override
    public void sync(Set<DefaultAuthorizeDefination> authorizeDefinations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Set<DefaultAuthorizeDefination>> entity =
                new HttpEntity<Set<DefaultAuthorizeDefination>>(authorizeDefinations, headers);
        restTemplate.postForEntity(authSyncURI, entity, Void.class);
    }
}
