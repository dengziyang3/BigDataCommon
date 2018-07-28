package com.hzgc.common.service.auth.annotation;

import java.lang.annotation.*;

/**
 * 资源提供者
 *
 * @author wangdl
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ResourceProvider {
    // 资源提供者的URL
    String uri();

    String type();
}
