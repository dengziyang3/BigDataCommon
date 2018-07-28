package com.hzgc.common.service.auth.scan;

import com.hzgc.common.service.auth.service.AuthorizeSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Set;

/**
 * @author liuzhikun
 * @date 2018/04/27
 */
@Slf4j
public class AuthorizeCodeAutoSyncRunner implements ApplicationRunner {
    private AuthorizeSyncService authorizeSyncService;

    private AuthorizeCodeAnnotationScanner scanner;

    public AuthorizeCodeAnnotationScanner getScanner() {
        return scanner;
    }

    public void setScanner(AuthorizeCodeAnnotationScanner scanner) {
        this.scanner = scanner;
    }

    public AuthorizeSyncService getAuthorizeSyncService() {
        return authorizeSyncService;
    }

    public void setAuthorizeSyncService(AuthorizeSyncService authorizeSyncService) {
        this.authorizeSyncService = authorizeSyncService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("Begin to scan authorize code...");

        Set<DefaultAuthorizeDefination> authorizeDefinationSet = scanner.scan();
        if (null != authorizeDefinationSet && !authorizeDefinationSet.isEmpty()) {
            authorizeSyncService.sync(authorizeDefinationSet);
        }
    }
}
