package com.springboot.demo.dao;


import com.springboot.demo.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        User user = sessionFactory.getCurrentSession().load(User.class, id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public User getUserByName(String name) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where name = :name")
                .setParameter("name", name).getSingleResult();
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}