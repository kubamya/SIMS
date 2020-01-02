package com.example.com.mapper;

import com.example.model.Com;
import org.springframework.stereotype.Service;

@Service
public interface ComMapper {

    /**
     * 根据comid获取com
     * @param com
     * @return
     */
    public Com getComById(Com com);
}
