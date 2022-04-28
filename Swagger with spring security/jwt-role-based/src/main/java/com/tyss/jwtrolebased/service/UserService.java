package com.tyss.jwtrolebased.service;


import java.util.List;

import com.tyss.jwtrolebased.pojo.User;
import com.tyss.jwtrolebased.view.UserDto;

public interface UserService {
    User save(UserDto user);
    User findOne(String username);
}
