package com.example.user.mapper;

import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 修改用户
     * @param user
     */
    public void updateUser(User user);

    /**
     * 删除用户
     * @param user
     */
    public void deleteUser(User user);

    /**
     * 根据用户Id查询用户
     * @param user
     * @return
     */
    public User getUserById(User user);

    /**
     * 根据deptId获取对应user
     * @param user
     * @return
     */
    public List<User> getUsersByDeptId(User user);
}
