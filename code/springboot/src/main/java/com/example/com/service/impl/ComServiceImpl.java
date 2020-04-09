package com.example.com.service.impl;

import com.example.com.mapper.ComMapper;
import com.example.com.service.ComService;
import com.example.model.Com;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComServiceImpl implements ComService {

    @Autowired
    private ComMapper comMapper;

    @Override
    public Com getComById(Com com) {
        return comMapper.getComById(com);
    }

	@Override
	public void addCom(Com com) {
		comMapper.addCom(com);		
	}

	@Override
	public void setComDisableById(Com com) {
		comMapper.setComDisableById(com);
	}

	@Override
	public void updateComById(Com com) {
		comMapper.updateComById(com);
	}
}
