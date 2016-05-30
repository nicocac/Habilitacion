//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Datos.TipoInsumoEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    public Main() {
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(String[] args) throws Exception {
        Session session = getSession();

        try {
            TipoInsumoEntity tipo = new TipoInsumoEntity();
            tipo.setTinNombre("Semillas");
            tipo.setTinDescripcion("Tipos de Semillas usadas para la siembra");
            Transaction tx = session.beginTransaction();
            session.save(tipo);
            tx.commit();
            session.close();
        } finally {
            session.close();
        }

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
