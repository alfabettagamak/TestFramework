package org.example.api.clients;

import org.example.api.models.Pet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateClient {

    private static SessionFactory sessionFactory;
    private static SessionFactory buildSessionFActory() {
        try{
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/ololo");
            properties.put("hibernate.connection.username", "postgres");
            properties.put("hibernate.connection.password", "12345");
            properties.put("hibernate.current_session_context_class", "thread");
            configuration.setProperties(properties);

            configuration.addAnnotatedClass(Pet.class);

            sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }
        catch (Exception exception){
            System.out.println("Sssion FActory creation failed!!!" + exception);
            throw new RuntimeException();
        }
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) sessionFactory = buildSessionFActory();
        return sessionFactory;
    }

}
