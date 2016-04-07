package com.hoteltop.dao.impl;

import com.hoteltop.dao.GenericDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Vlastelin on 06.04.2016.
 */
public abstract class GenericDAOImpl <T extends Serializable> implements GenericDAO<T> {

    private Class entityClass;

    private static SessionFactory sessionFactory;

    public static void init() {
        Configuration configuration = new Configuration();
        configuration.configure();
//        ServiceRegistry registry = StandardServiceRegistryBuilder.
//                SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
    }

    public GenericDAOImpl() {
//        ParameterizedType parameterizedType = this.getClass().getGenericInterfaces();
    }

    public void create(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
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
}
