package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    @SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	
//        	Configuration configuration = new Configuration();
//        	configuration.configure();
//        	
//        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        	return sessionFactory;
        	
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    //---------------------------------------------------
//    
//	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("funlingo");
//	
//	public static EntityManager getEntityManager(){
//		return factory.createEntityManager();
//	}
//	
//	public static void close(){
//		factory.close();
//	}

}
