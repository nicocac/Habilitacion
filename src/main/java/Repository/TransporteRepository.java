package Repository;

import Conexion.Conexion;
import Datos.TransporteEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class TransporteRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<TransporteEntity> getAllTransportes(){
        List<TransporteEntity> listaInsumo = new ArrayList<>();
        Session session = null;
        Transaction tx = null;
        try {
             session = Conexion.getSessionFactory().getCurrentSession();
             tx = session.beginTransaction();

            TransporteEntity insumo;
            Query query = session.createQuery("select x from TransporteEntity x");
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (TransporteEntity) iter.next();
                listaInsumo.add(insumo);
            }
            tx.rollback();
        }catch (Exception e){

        } finally {

            return listaInsumo;

        }
        //session.close();
    }


    public TransporteEntity getTransporteByNombre(String nombre){
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        TransporteEntity insumo = new TransporteEntity();
        Query query = session.createQuery("select x from TransporteEntity x where ucase(transporteNombre) like ucase(:pNombre) and transporteFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (TransporteEntity) iter.next();
        }
        //session.close();
        return insumo;
    }


    public TransporteEntity getTransporteById(Long id){
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        TransporteEntity insumo = new TransporteEntity();
        Query query = session.createQuery("select x from TransporteEntity x where ucase(transporteNombre) like ucase(:pId) and transporteFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (TransporteEntity) iter.next();
        }
        //session.close();
        return insumo;
    }

}
