package com.hzgc.common.service.journal.service;

import com.hzgc.common.service.journal.dto.OperateLogDTO;
import com.hzgc.common.service.response.ResponseResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuzhikun
 * @date 2018/05/28
 */
public class OperateLogWriterServiceImpl implements OperateLogWriterService {
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    @Override
    public void writeLog(OperateLogDTO operateLogDTO) {
        String url = "http://journal/operatelog/add_logs";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OperateLogDTO> entity = new HttpEntity<OperateLogDTO>(operateLogDTO, headers);
        restTemplate.postForObject(url, entity, ResponseResult.class);
    }
}
