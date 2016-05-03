package com.hoteltop.dao.impl;

import com.hoteltop.dao.GenericDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Vlastelin on 06.04.2016.
 */
abstract class GenericDAOImpl <T extends Serializable> implements GenericDAO<T> {

    private static final int ELEM_PER_PAGE = 2;

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    protected GenericDAOImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().
                getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T create(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        session.getTransaction().commit();
        return entity;
    }

    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        session.getTransaction().commit();
    }

    public T findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(entityClass, id);
    }

    public List<T> getList(int page) {
        Criteria criteria = getCriteria();
        return criteria
                .setFirstResult((page - 1) * ELEM_PER_PAGE)
                .setMaxResults(ELEM_PER_PAGE).list();
    }

    public long getPageCount() {
        Criteria criteria = getCriteria();
        criteria.setProjection(Projections.rowCount());
        return (long) criteria.uniqueResult() / ELEM_PER_PAGE;
    }

    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(entityClass).list();
    }

    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
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
        return session.createCriteria(entityClass);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
