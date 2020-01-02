package com.example.com.service;

import com.example.model.Com;
import org.springframework.stereotype.Service;

@Service
public interface ComService {

    /**
     * 根据comid获取com
     * @param com
     * @return
     */
    public Com getComById(Com com);
}
