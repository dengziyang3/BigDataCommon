package com.hzgc.common.service.auth.annotation;

import java.lang.annotation.*;

/**
 * 权限码
 *
 * @author liuzhikun
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AuthorizeCode {


    /**
     * 权限id
     *
     * @return id
     */
    int id();
    /**
     * 权限名称
     *
     * @return name
     */
    String name();

    /**
     * 权限对应的菜单
     *
     * @return menu
     */
    String menu();

    /**
     * 描述
     *
     * @return description
     */
    String description() default "";
}
