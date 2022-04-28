package com.tyss.cloudgateway.service;


import com.tyss.cloudgateway.pojo.User;
import com.tyss.cloudgateway.view.UserDto;

public interface UserService {
    User save(UserDto user);
    User findOne(String username);
}
