package com.hoteltop.dao;

/**
 * Created by Vlastelin on 06.04.2016.
 */

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T extends Serializable> {

    void create(T entity);

    void update(T entity);

    T findById(Long id);

    List<T> findAll();

    void delete(T entity);

    void merge(T entity);

    void detach(T entity);
}
