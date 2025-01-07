package com.mycompany.mavenproject1.helper;
import javax.servlet.ServletContextEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class FactoryProvider {
    private static SessionFactory factory;
    public static SessionFactory getfactory(){
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        return factory;
}

    
}