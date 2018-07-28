package com.hzgc.common.service.journal.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 记录系统操作日志
 *
 * @author liuzk
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface SysOperateLog {
    /**
     * 操作类型
     *
     * @return 操作类型
     */
    int operateType();

    /**
     * 业务类型
     *
     * @return 业务类型
     */
    int serviceType();

    /**
     * 操作描述
     *
     * @return 操作描述信息
     */
    String description();

    /**
     * spring EL 参数
     *
     * @return 参数
     */
    String[] params() default "";
}
