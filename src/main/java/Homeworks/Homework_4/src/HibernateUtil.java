package Homeworks.Homework_4.src;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.io.File;
import java.util.Set;

/**
 * Created by masaki on 4/26/2017.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {

        try {
            Configuration configuration = new Configuration()
                    .configure(new File("hibernate.cfg.xml"));

            Reflections reflections = new Reflections("src.entity");
            Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);
            for (Class<?> entity : entities) {
                configuration.addAnnotatedClass(entity);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
