package com.example.organ.service;

import com.example.model.Com;
import com.example.model.Dept;
import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganService {
	
	/**
	 * 获取所属组织
	 * @param dept
	 * @return
	 */
	public Com getParentComInfo(Dept dept);

    /**
     * 根据id获取com
     * @param com
     * @return
     */
    public Com getComById(Com com);

    /**
     * 根据pid获取dept
     * @return
     */
    public List<Dept> getDeptByPid(Dept dept);

    /**
     * 根据comid获取depts
     * @param dept
     * @return
     */
    public List<Dept> getDeptByComid(Dept dept);

    /**
     * 根据deptid获取user
     * @param user
     * @return
     */
    public List<User> getUserByDeptId(User user);

    /**
     * 获取所有的com
     * @return
     */
    public List<Com> getAllCom();
}
