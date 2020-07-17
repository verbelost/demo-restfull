package com.springboot.demo.dao;


import com.springboot.demo.model.Role;

public interface RoleDao {
    void addRole(Role role);
    Role getRoleByName(String name);
    Long countRoles(String name);
}
