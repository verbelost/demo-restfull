package com.springboot.demo.service;

import com.springboot.demo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user, List<String> rolesValues);
    void removeUser(Long id);
    User getUserById(Long id);
    List<User> listUsers();

}
