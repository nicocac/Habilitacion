package Repository;

import Conexion.Conexion;
import Datos.InsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class InsumoRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<InsumoEntity> getAllInsumos() {
        List<InsumoEntity> listaInsumo = new ArrayList<>();
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        InsumoEntity insumo;
        Query query = session.createQuery("select x from InsumoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
            listaInsumo.add(insumo);
        }
        //session.close();
        return listaInsumo;
    }


    public InsumoEntity getInsumoByNombre(String nombre) {
        Session session = null;
        Transaction tx = null;
        InsumoEntity insumo = new InsumoEntity();

        try {

            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select x from InsumoEntity x where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
            query.setParameter("pNombre", nombre);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (InsumoEntity) iter.next();
            }
            tx.rollback();
        } catch (Exception e) {
            tx.rollback();
            System.out.print(e.toString());
        } finally {
            //session.close();
            return insumo;

        }
    }


    public InsumoEntity getInsumoByNombrePlanificar(String nombre) {
        Session session = null;
//        Transaction tx = null;
        InsumoEntity insumo = new InsumoEntity();

        try {

            session = Conexion.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();

            Query query = session.createQuery("select x from InsumoEntity x where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
            query.setParameter("pNombre", nombre);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (InsumoEntity) iter.next();
            }
//            tx.rollback();
        } catch (Exception e) {
//            tx.rollback();
            System.out.print(e.toString());
        } finally {
            //session.close();
            return insumo;

        }
    }


    public InsumoEntity getInsumoById(Integer id) {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insId) like ucase(:pId) and insFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        tx.rollback();
        //session.close();
        return insumo;
    }

    public List<InsumoEntity> getAllInsumosByTipo(Integer tipoId) {
        List<InsumoEntity> listaInsumo = new ArrayList<>();
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        InsumoEntity insumo;
        Query query = session.createQuery("select x from InsumoEntity x where x.tipoInsumoByInsTinId.id = :tipoId");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
            listaInsumo.add(insumo);
        }
        //session.close();
        return listaInsumo;
    }

}
