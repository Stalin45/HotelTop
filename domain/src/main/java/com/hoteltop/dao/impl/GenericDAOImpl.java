package com.hoteltop.dao.impl;

import com.hoteltop.dao.GenericDAO;
import com.hoteltop.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Vlastelin on 06.04.2016.
 */
public abstract class GenericDAOImpl <T extends Serializable> implements GenericDAO<T> {

    private Class<T> entityClass;

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public GenericDAOImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().
                getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T create(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        return entity;
    }

    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    public T findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(entityClass, id);
    }

    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(entityClass).list();
    }

    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public void merge(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.refresh(entity);
    }

    public void detach(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.evict(entity);
    }

    public Criteria getCriteria() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        return session.createCriteria(entityClass);
    }
}
