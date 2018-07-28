package com.hzgc.common.service.journal.config;

import com.hzgc.common.service.journal.aop.SysOperateLogAspect;
import com.hzgc.common.service.journal.helper.OperateLogWriterHelper;
import com.hzgc.common.service.journal.service.OperateLogWriterService;
import com.hzgc.common.service.journal.service.OperateLogWriterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

/**
 * @author liuzhikun
 * @date 2018/05/28
 */
@Configuration
@Order(LOWEST_PRECEDENCE)
@Import(RestTemplateConfiguration.class)
public class JournalAutoConfiguration {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public OperateLogWriterService operateLogWriterService() {
        OperateLogWriterServiceImpl operateLogWriterService = new OperateLogWriterServiceImpl();
        operateLogWriterService.setRestTemplate(restTemplate);
        return operateLogWriterService;
    }

    @Bean
    public SysOperateLogAspect sysOperateLogAspect() {
        return new SysOperateLogAspect();
    }

    @Bean
    public OperateLogWriterHelper operateLogWriterHelper() {
        OperateLogWriterHelper operateLogWriterHelper = new OperateLogWriterHelper();
        operateLogWriterHelper.setOperLogger(operateLogWriterService());
        return operateLogWriterHelper;
    }
}
