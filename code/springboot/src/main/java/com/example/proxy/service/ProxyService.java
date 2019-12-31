package com.example.proxy.service;

import com.example.model.City;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ProxyService {

    /**
     * 根据城市名称获取城市代码
     * @param queryParams
     * @return
     */
    public City getCityCode(Map<String, Object> queryParams);
}
