package com.hoteltop.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.internal.SessionFactoryRegistry;
import org.hibernate.mapping.MetadataSource;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Vlastelin on 11.04.2016.
 */
public class HibernateUtil {

    private static volatile SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        if (sessionFactory == null) {
            synchronized (HibernateUtil.class) {
                if (sessionFactory == null) {
                    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                    return new MetadataSources(registry).buildMetadata().buildSessionFactory();
                } else {
                    return getSessionFactory();
                }
            }
        } else {
            return getSessionFactory();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
