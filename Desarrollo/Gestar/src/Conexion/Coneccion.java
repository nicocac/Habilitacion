package Conexion;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Coneccion {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    static {
        try {
            Configuration ex = new Configuration();
            ex.configure();
            serviceRegistry = (new ServiceRegistryBuilder()).applySettings(ex.getProperties()).buildServiceRegistry();
            ourSessionFactory = ex.buildSessionFactory(serviceRegistry);
        } catch (Throwable var1) {
            throw new ExceptionInInitializerError(var1);
        }
    }
}
