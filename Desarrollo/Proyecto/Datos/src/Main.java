import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Map;

/**
 * Created by OWNER on 5/29/2016.
 */
public class Main {
/**    private static final SessionFactory ourSessionFactory
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    cfg.getProperties()). buildServiceRegistry();
            SessionFactory ourSessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    } */

    public static Session getSession() throws HibernateException {
       //return ourSessionFactory.openSession();
        SessionFactory ourSessionFactory;
        try {
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    cfg.getProperties()). buildServiceRegistry();
           ourSessionFactory = cfg.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            TipoInsumoDao tipo = new TipoInsumoDao();
            tipo.setNombre("Semillas");
            tipo.setDescripcion("Tipos de Semillas usadas para la siembra");
            Transaction tx = session.beginTransaction();
            session.save(tipo);
            tx.commit();
            session.close();
        } finally {
            session.close();
        }
    }
}
