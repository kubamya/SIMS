package com.example.organ.service.impl;

import com.example.com.mapper.ComMapper;
import com.example.model.Com;
import com.example.model.Dept;
import com.example.model.User;
import com.example.organ.mapper.OrganMapper;
import com.example.organ.service.OrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrganServiceImpl implements OrganService {

    @Autowired
    private ComMapper comMapper;

    @Autowired
    private OrganMapper organMapper;

    @Override
    public Com getComById(Com com) {
        return comMapper.getComById(com);
    }

    @Override
    public List<Dept> getDeptByPid(Dept dept) {
        return organMapper.getDeptByPid(dept);
    }

    @Override
    public List<Dept> getDeptByComid(Dept dept) {
        return organMapper.getDeptByComid(dept);
    }

    @Override
    public List<User> getUserByDeptId(User user) {
        return organMapper.getUserByDeptId(user);
    }

    @Override
    public List<Com> getAllCom() {
        return organMapper.getAllCom();
    }

	@Override
	public Com getParentComInfo(Dept dept) {
		return organMapper.getParentComInfo(dept);
	}

	@Override
	public Map<String, Object> getUserParentInfo(User user) {
		return organMapper.getUserParentInfo(user);
	}
}
