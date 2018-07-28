package com.hzgc.common.service.journal.aop;

import com.hzgc.common.service.journal.annotation.SysOperateLog;
import com.hzgc.common.service.journal.helper.OperateLogWriterHelper;
import com.hzgc.common.service.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作日志切面
 *
 * @author liuzk
 */
@Aspect
@Slf4j
@SuppressWarnings("rawtypes")
public class SysOperateLogAspect {
    private ExpressionEvaluator<String> evaluator = new ExpressionEvaluator<>();

    /**
     * 写操作日志aop
     *
     * @param jp jp
     * @param rl rl
     * @return object
     * @throws Throwable exception
     */
    @Around("execution(* *.*(..)) && @annotation(rl)")
    public Object writeOperateLog(ProceedingJoinPoint jp, SysOperateLog rl) throws Throwable {
        String className = jp.getTarget().getClass().toString();
        className = className.substring(className.indexOf("com"));

        int serviceType = rl.serviceType();
        int operateType = rl.operateType();
        String description = rl.description();
        String[] params = rl.params();

        // 使用spring EL表达式解析描述字段
        List<String> paramValues = new ArrayList<>();
        for (String param : params) {
            if (!StringUtils.isEmpty(param)) {
                paramValues.add(getValue(jp, param));
            }
        }
        String desc = MessageFormat.format(description, paramValues.toArray());

        Object object;
        try {
            object = jp.proceed();
            if (object instanceof ResponseResult) {
                if (((ResponseResult) object).getHead().getErrorCode() == 0) {
                    success(serviceType, operateType, desc);
                } else {
                    failed(serviceType, operateType, desc, ((ResponseResult) object).getHead().getMessage());
                }
            } else {
                success(serviceType, operateType, desc);
            }
        } catch (Throwable throwable) {
            failed(serviceType, operateType, desc, throwable.getMessage());
            throw throwable;
        }
        return object;
    }


    private String getValue(JoinPoint joinPoint, String condition) {
        return getValue(joinPoint.getTarget(), joinPoint.getArgs(),
                joinPoint.getTarget().getClass(),
                ((MethodSignature) joinPoint.getSignature()).getMethod(), condition);
    }

    private String getValue(Object object, Object[] args, Class clazz, Method method,
                            String condition) {
        if (args == null) {
            return null;
        }
        EvaluationContext evaluationContext =
                evaluator.createEvaluationContext(object, clazz, method, args);
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, clazz);
        return evaluator.condition(condition, methodKey, evaluationContext, String.class);
    }

    private void success(int serviceType, int operateType, String description) {
        try {
            OperateLogWriterHelper.success(serviceType, operateType, description);
        } catch (Exception e) {
            log.error("Write operate log error ", e);
        }
    }

    private void failed(int serviceType, int operateType, String description, String reason) {
        try {
            OperateLogWriterHelper.fail(serviceType, operateType, description, reason);
        } catch (Exception e) {
            log.error("Write operate log error ", e);
        }
    }
}
