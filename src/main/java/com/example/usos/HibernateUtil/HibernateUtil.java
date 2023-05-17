package com.example.usos.HibernateUtil;

import com.example.usos.StudentMethods.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static{
        try{
            //rejestr
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            MetadataSources metadataSources = new MetadataSources(registry);

            metadataSources.addAnnotatedClass(Student.class);
            metadataSources.addAnnotatedClass(Group.class);
            metadataSources.addAnnotatedClass(Subject.class);
            metadataSources.addAnnotatedClass(Grade.class);
            metadataSources.addAnnotatedClass(Rating.class);

            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        } catch (Exception e){
            throw new ExceptionInInitializerError("Błąd inicjalizacji Hibernate");
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
