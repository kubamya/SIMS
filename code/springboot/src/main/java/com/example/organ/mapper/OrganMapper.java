package com.example.organ.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.model.Com;
import com.example.model.Dept;
import com.example.model.User;

@Service
public interface OrganMapper {
	
	/**
	 * 获取user的父级信息
	 * @param user
	 * @return
	 */
	public Map<String, Object> getUserParentInfo(User user);
	
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
     * 根据deptid获取user
     * @param user
     * @return
     */
    public List<User> getUserByDeptId(User user);

    /**
     * 根据comid获取depts
     * @param dept
     * @return
     */
    public List<Dept> getDeptByComid(Dept dept);

    /**
     * 获取所有的com
     * @return
     */
    public List<Com> getAllCom();

}
