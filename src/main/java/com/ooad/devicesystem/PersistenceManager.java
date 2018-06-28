package com.ooad.devicesystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersistenceManager{
    private SessionFactory sessionFactory;
    public PersistenceManager( SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}