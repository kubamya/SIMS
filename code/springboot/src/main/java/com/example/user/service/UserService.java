package com.example.user.service;

import com.example.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

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
}
