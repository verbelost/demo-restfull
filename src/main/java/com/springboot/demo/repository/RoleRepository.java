package com.springboot.demo.repository;

import com.springboot.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Long countRoleByName(String name);
    Role getRoleByName(String name);
}
