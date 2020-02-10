package com.example.dept.service;

import com.example.model.Com;
import com.example.model.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {

    /**
     * 根据ComId获取所有dept
     * @param com
     * @return
     */
    public List<Dept> getDeptsByComId(Com com);

    /**
     * 根据deptId获取dept
     * @param dept
     * @return
     */
    public Dept getDeptById(Dept dept);
}
