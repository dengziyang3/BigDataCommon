package com.hzgc.common.service.auth.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author liuzhikun
 * @date 2018/05/19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AuthSyncConfiguration.class)
public @interface EnableAuthSynchronize {
}
