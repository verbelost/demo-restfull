package com.springboot.demo.dao;



import com.springboot.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUserById(int id);
    User getUserByName(String name);
    List<User> listUsers();
}
