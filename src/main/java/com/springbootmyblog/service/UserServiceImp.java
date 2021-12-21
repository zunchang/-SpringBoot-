package com.springbootmyblog.service;

import com.springbootmyblog.mapper.UserMapper;
import com.springbootmyblog.model.User;
import com.springbootmyblog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImp implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user=userMapper.examination(username, MD5Util.code(password));
        return user;
    }
}
