package com.hoteltop.dao;

/**
 * Created by Vlastelin on 06.04.2016.
 */

import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T extends Serializable> {

    public T create(T entity);

    public void update(T entity);

    public T findById(Long id);

    public List<T> getList(int page);

    public long getPageCount();

    public List<T> findAll();

    public void delete(T entity);

    public void merge(T entity);

    public void detach(T entity);
}
