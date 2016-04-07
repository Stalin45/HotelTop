package com.hoteltop.dao;

/**
 * Created by Vlastelin on 06.04.2016.
 */

import java.io.Serializable;

public interface GenericDAO <T extends Serializable> {

    void create(T entity);

    void update(T entity);

    T get(Long id);

    void delete(T entity);

    void merge(T entity);

    void detach(T entity);
}
