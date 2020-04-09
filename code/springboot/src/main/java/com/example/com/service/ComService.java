package com.example.com.service;

import com.example.model.Com;
import org.springframework.stereotype.Service;

@Service
public interface ComService {
	
	/**
	 * 根据comid更新com
	 * @param com
	 */
	public void updateComById(Com com);
	
	/**
	 * 根据comid删除com
	 * @param com
	 */
	public void setComDisableById(Com com);
	
	/**
	 * 新增com信息
	 * @param com
	 */
	public void addCom(Com com);

    /**
     * 根据comid获取com
     * @param com
     * @return
     */
    public Com getComById(Com com);
    
}
