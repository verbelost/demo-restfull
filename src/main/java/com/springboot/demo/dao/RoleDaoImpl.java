package com.springboot.demo.dao;


import com.springboot.demo.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void addRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("from Role where name = :name")
                .setParameter("name", name).getSingleResult();
    }

    @Transactional
    @Override
    public Long countRoles(String name) {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Role where name = :name")
                .setParameter("name", name).getSingleResult();
    }
}
