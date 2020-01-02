package com.example.dept.service.impl;

import com.example.dept.mapper.DeptMapper;
import com.example.dept.service.DeptService;
import com.example.model.Com;
import com.example.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getDeptsByComId(Com com) {
        return deptMapper.getDeptsByComId(com);
    }
}
