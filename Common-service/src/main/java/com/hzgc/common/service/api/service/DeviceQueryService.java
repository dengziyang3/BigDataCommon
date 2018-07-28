package com.hzgc.common.service.api.service;

import com.hzgc.common.service.api.bean.DeviceDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DeviceQueryService {
    @Autowired
    @SuppressWarnings("unused")
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getDeviceInfoByIpcError")
    @SuppressWarnings("unused")
    public DeviceDTO getDeviceInfoByIpc(String ipcId) {
        if (!StringUtils.isBlank(ipcId)) {
            return restTemplate.getForObject("http://device/internal/devices/query_by_serial/" + ipcId, DeviceDTO.class);
        }
        return new DeviceDTO();
    }

    @SuppressWarnings("unused")
    public DeviceDTO getDeviceInfoByIpcError(String ipcId) {
        log.error("Get deivce info by ipc error, ipcId is:" + ipcId);
        return new DeviceDTO();
    }

    @HystrixCommand(fallbackMethod = "getDeviceInfoByBatchIpcError")
    @SuppressWarnings("all")
    public Map<String, DeviceDTO> getDeviceInfoByBatchIpc(List<String> ipcList) {
        if (ipcList != null && ipcList.size() > 0) {
            ParameterizedTypeReference<Map<String, DeviceDTO>>
                    parameterizedTypeReference = new ParameterizedTypeReference<Map<String, DeviceDTO>>() {
            };
            ResponseEntity<Map<String, DeviceDTO>> responseEntity =
                    restTemplate.exchange("http://device/internal/devices/batch_query_by_serial",
                            HttpMethod.POST,
                            new HttpEntity<>(ipcList),
                            parameterizedTypeReference);
            return responseEntity.getBody();
        }
        return new HashMap<>();
    }

    @SuppressWarnings("unused")
    public Map<String, DeviceDTO> getDeviceInfoByBatchIpcError(List<String> ipcList) {
        log.error("Get device info by batch ipc error, ipcId list is:" + ipcList);
        return new HashMap<>();
    }

    @SuppressWarnings("unused")
    public List<Long> query_device_id(Long areaId, String level){
        if (areaId != null){
            return  restTemplate.getForObject("http://region/internal/region/query_device_id/" + areaId + "/" + level, List.class);
        }
        return null;
    }

    @HystrixCommand(fallbackMethod = "getDeviceInfoByIdError")
    @SuppressWarnings("unused")
    public DeviceDTO getDeviceInfoById(Long id) {
        if (id != null) {
            return restTemplate.getForObject("http://device/internal/devices/query_by_id/" + id, DeviceDTO.class);
        }
        return new DeviceDTO();
    }

    @SuppressWarnings("unused")
    public DeviceDTO getDeviceInfoByIdError(Long id) {
        log.error("Get device info by id error, id is:" + id);
        return new DeviceDTO();
    }

    @HystrixCommand(fallbackMethod = "getDeviceInfoByBatchIdError")
    public Map<String, DeviceDTO> getDeviceInfoByBatchId(List<Long> idList) {
        if (idList != null && idList.size() > 0) {
            ParameterizedTypeReference<Map<String, DeviceDTO>>
                    parameterizedTypeReference = new ParameterizedTypeReference<Map<String, DeviceDTO>>() {
            };
            ResponseEntity<Map<String, DeviceDTO>> responseEntity =
                    restTemplate.exchange("http://device/internal/devices/batch_query_by_id",
                            HttpMethod.POST,
                            new HttpEntity<>(idList),
                            parameterizedTypeReference);
            return responseEntity.getBody() == null ? new HashMap<>() : responseEntity.getBody();
        }
        return new HashMap<>();
    }

    @SuppressWarnings("unused")
    public Map<String, DeviceDTO> getDeviceInfoByBatchIdError(List<Long> idList) {
        log.error("Get device info by batch id error, id List is:" + idList);
        return new HashMap<>();
    }
}