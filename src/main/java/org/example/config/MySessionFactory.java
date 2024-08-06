package org.example.config;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class MySessionFactory {
    private static MySessionFactory instance;

    private final SessionFactory sessionFactory;

    private MySessionFactory(){
        // custom
//        try {
//            Properties properties = new Properties();
//            properties.load(ClassLoader.getSystemResourceAsStream("hibernate.properties"));
//            sessionFactory = new Configuration().addProperties(properties).buildSessionFactory();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        if (instance == null) instance = new MySessionFactory();
        return instance.sessionFactory;
    }
}