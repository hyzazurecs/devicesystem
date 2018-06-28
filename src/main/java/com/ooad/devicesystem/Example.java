package com.ooad.devicesystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class Example {
    private static final Logger log = LoggerFactory.getLogger(Example.class);

    private SessionFactory sessionFactory;
    public Example( SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void initTestData() throws Exception{

    }

    public void demo() {

    }


}
