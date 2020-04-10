package com.example.dept.mapper;

import com.example.model.Com;
import com.example.model.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptMapper {
	
	/**
	 * 更新dept
	 * @param dept
	 */
	public void updateDeptById(Dept dept);
	
	/**
	 * 逻辑删除dept
	 * @param dept
	 */
	public void setDeptDisableById(Dept dept);
	
	/**
	 * 新增dept
	 * @param dept
	 */
	public void addDept(Dept dept);

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
