package com.example.user.service.impl;

import com.example.model.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }

    @Override
    public User getUserById(User user) {
        return userMapper.getUserById(user);
    }

    @Override
    public List<User> getUsersByDeptId(User user) {
        return userMapper.getUsersByDeptId(user);
    }
}
