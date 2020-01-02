package com.example.dept.mapper;

import com.example.model.Com;
import com.example.model.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptMapper {

    /**
     * 根据ComId获取所有dept
     * @param com
     * @return
     */
    public List<Dept> getDeptsByComId(Com com);
}
