package Repository;

import Conexion.Conexion;
import Datos.DetalleOrdenEntity;
import Datos.InsumoEntity;
import Datos.OrdenTrabajoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class OrdenRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<OrdenTrabajoEntity> getAllOrdenes() {
        List<OrdenTrabajoEntity> listaInsumo = new ArrayList<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            OrdenTrabajoEntity insumo;
            Query query = session.createQuery("select x from OrdenTrabajoEntity x");
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (OrdenTrabajoEntity) iter.next();
                listaInsumo.add(insumo);
            }
            tx.rollback();
        } catch (Exception e) {

        } finally {

        }
        //session.close();
        return listaInsumo;
    }


    public List<DetalleOrdenEntity> getAllDetalleOrdenesByOrdenID(Integer ordenID) {
        Session session = null;
        Transaction tx = null;

        List<DetalleOrdenEntity> listaDetalleOrden = new ArrayList<>();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            DetalleOrdenEntity detalleOrden;
            Query query = session.createQuery("select x from DetalleOrdenEntity x where x.orden.id = (:ordenID) ");
            query.setParameter("ordenID", ordenID);

            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                detalleOrden = (DetalleOrdenEntity) iter.next();
                listaDetalleOrden.add(detalleOrden);
            }
            tx.rollback();
        } catch (Exception e) {

        } finally {
            return listaDetalleOrden;

        }
        //session.close();
    }






    public List<DetalleOrdenEntity> getAllDetalleOrdenesByOrdenIDSinTX(Integer ordenID) {
        Session session = null;
        Transaction tx = null;

        List<DetalleOrdenEntity> listaDetalleOrden = new ArrayList<>();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
            DetalleOrdenEntity detalleOrden;
            Query query = session.createQuery("select x from DetalleOrdenEntity x where x.orden.id = (:ordenID) ");
            query.setParameter("ordenID", ordenID);

            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                detalleOrden = (DetalleOrdenEntity) iter.next();
                listaDetalleOrden.add(detalleOrden);
            }
//            tx.rollback();
        } catch (Exception e) {

        } finally {
            return listaDetalleOrden;

        }
        //session.close();
    }


    public int deleteAllDetalleOrdenesByOrdenID(Integer ordenID) {
        Boolean flag = false;
        int result = 0;
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("delete from DetalleOrdenEntity x " +
                "where x.orden.id = (:ordenID) ");
        query.setParameter("ordenID", ordenID);
        result = query.executeUpdate();
//        List list = query.list();
//        Iterator iter = list.iterator();
//        while (iter.hasNext()) {
//            insumo = (DetalleOrdenEntity) iter.next();
//            listaInsumo.add(insumo);
//        }
//        if(list.size() ==0){
//            flag = true;
//        }
//        session.update();
        //session.close();
        return result;
    }


    public InsumoEntity getInsumoByNombre(String nombre) {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        //session.close();
        return insumo;
    }


    public OrdenTrabajoEntity getOrdenById(Integer id) {
        Session session = null;
        Transaction tx = null;
        OrdenTrabajoEntity insumo = new OrdenTrabajoEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select x from OrdenTrabajoEntity x " +
                    "where ucase(id) like ucase(:pId) and fechaBaja is null");
            query.setParameter("pId", id);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (OrdenTrabajoEntity) iter.next();
            }
            tx.rollback();
        } catch (Exception e) {

        } finally {
            return insumo;

        }
        //session.close();
    }

}
