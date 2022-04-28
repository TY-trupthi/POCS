package com.tyss.cloudgateway.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tyss.cloudgateway.pojo.User;
import com.tyss.cloudgateway.repo.UserDao;
import com.tyss.cloudgateway.view.UserDto;

//@Service(value = "userService")
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		  user.getRoles().forEach(role -> { authorities.add(new
		  SimpleGrantedAuthority("ROLE_" + role)); }); 
        return authorities;
    }

    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User save(UserDto user) {

        User nUser = new User();
        BeanUtils.copyProperties(user, nUser);
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(nUser);
    }
}
