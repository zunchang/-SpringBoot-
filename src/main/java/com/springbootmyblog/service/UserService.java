package com.springbootmyblog.service;


import com.springbootmyblog.model.User;


public interface UserService {
        public User checkUser(String username, String password);
}
