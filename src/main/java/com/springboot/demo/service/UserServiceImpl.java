package com.springboot.demo.service;

import com.springboot.demo.dao.RoleDao;
import com.springboot.demo.dao.UserDao;
import com.springboot.demo.model.Role;
import com.springboot.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void addUser(User user, List<String> rolesValues) {
        makeChanges(user, rolesValues);
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user, List<String> rolesValues) {
        makeChanges(user, rolesValues);
        userDao.updateUser(user);
    }

    private void makeChanges(User user, List<String> rolesValues) {
        Set<Role> roles = new HashSet<>();
        for (String role: rolesValues) {
            if (roleDao.countRoles(role) > 0) {
                roles.add(roleDao.getRoleByName(role));
            } else {
                Role newRole = new Role();
                newRole.setName(role);
                roleDao.addRole(newRole);
                roles.add(newRole);
            }
        }
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

}
