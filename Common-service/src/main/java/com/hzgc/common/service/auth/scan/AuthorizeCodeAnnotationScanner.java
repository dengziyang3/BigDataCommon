package com.hzgc.common.service.auth.scan;

import com.hzgc.common.service.auth.annotation.AuthorizeCode;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 权限颗粒注解扫描器
 *
 * @author liuzhikun
 */
@Slf4j
public class AuthorizeCodeAnnotationScanner {
    private List<String> basePackages;

    @Value("${spring.application.name}")
    private String applicationName;

    private Set<DefaultAuthorizeDefination> authorizeDefinationSetCache = new LinkedHashSet<>();

    public List<String> getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(List<String> basePackages) {
        this.basePackages = basePackages;
    }

    public Set<DefaultAuthorizeDefination> scan() {
        if (CollectionUtils.isEmpty(basePackages)) {
            return null;
        }

        Reflections reflections = new Reflections(StringUtils.toStringArray(basePackages), new FieldAnnotationsScanner());
        Set<Field> authorizeCodeSet = reflections.getFieldsAnnotatedWith(AuthorizeCode.class);
        if (null == authorizeCodeSet || authorizeCodeSet.isEmpty()) {
            return null;
        }

        authorizeCodeSet.forEach(f -> {
            DefaultAuthorizeDefination authorizeDefination = parseAuthorizeCode(f);
            if (authorizeDefinationSetCache.contains(authorizeDefination)) {
                throw new RuntimeException("权限码重复，" + authorizeDefination.toString());
            }
            authorizeDefinationSetCache.add(authorizeDefination);
        });
        return authorizeDefinationSetCache;
    }

    private DefaultAuthorizeDefination parseAuthorizeCode(Field authField) {
        try {

            DefaultAuthorizeDefination defaultAuthorizeDefination = new DefaultAuthorizeDefination();
            defaultAuthorizeDefination.setPermission((String) authField.get(null));
            defaultAuthorizeDefination.setApplicationName(applicationName);
            Map<String, Object> authorizeInfo = AnnotationUtils.getAnnotationAttributes(authField.getAnnotation(AuthorizeCode.class));
            if (null == authorizeInfo || authorizeInfo.isEmpty()) {
                throw new IllegalArgumentException("权限颗参数配置错误");
            }

            for (Map.Entry<String, Object> entry : authorizeInfo.entrySet()) {
                if ("name".equals(entry.getKey())) {
                    defaultAuthorizeDefination.setName((String) entry.getValue());
                } else if ("menu".equals(entry.getKey())) {
                    defaultAuthorizeDefination.setMenu((String) entry.getValue());
                } else if ("description".equals(entry.getKey())) {
                    defaultAuthorizeDefination.setDescription((String) entry.getValue());
                } else if("id".equals(entry.getKey())){
                    defaultAuthorizeDefination.setId((Integer)entry.getValue());
                }
            }

            log.info("Found new authorize code, {}", defaultAuthorizeDefination);

            return defaultAuthorizeDefination;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
