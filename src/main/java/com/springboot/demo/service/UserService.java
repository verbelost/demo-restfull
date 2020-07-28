package com.springboot.demo.service;

import com.springboot.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user, String[] rolesValues);
    void removeUser(Long id);
    User getUserById(Long id);
    List<User> listUsers();

}
