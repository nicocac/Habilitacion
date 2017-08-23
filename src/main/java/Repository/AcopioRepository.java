package Repository;

import Conexion.Conexion;
import Datos.AcopioEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class AcopioRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<TipoInsumoEntity> getAllTipoInsumos(){
        List<TipoInsumoEntity> listaTipoInsumo = new ArrayList<>();
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        TipoInsumoEntity tipoInsumo;
        Query query = session.createQuery("select x from TipoInsumoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoInsumoEntity) iter.next();
            listaTipoInsumo.add(tipoInsumo);
        }
        //session.close();
        return listaTipoInsumo;
    }


    public AcopioEntity getAcopioByCodigo(Integer codigo){
        Session session = null;
        Transaction tx = null;
        AcopioEntity tipoGrano = new AcopioEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select x from AcopioEntity x where ucase(codigo) like ucase(:pNombre) and acopioFechaBaja is null");
            query.setParameter("pNombre", codigo);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                tipoGrano = (AcopioEntity) iter.next();
            }
            tx.rollback();
        }catch (Exception e){

        }finally {
            return tipoGrano;

        }
        //session.close();
    }





    public AcopioEntity getAcopioByCodigoSinTX(Integer codigo){
        Session session = null;
//        Transaction tx = null;
        AcopioEntity tipoGrano = new AcopioEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();

            Query query = session.createQuery("select x from AcopioEntity x where ucase(codigo) like ucase(:pNombre) and acopioFechaBaja is null");
            query.setParameter("pNombre", codigo);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                tipoGrano = (AcopioEntity) iter.next();
            }
//            tx.rollback();
        }catch (Exception e){

        }finally {
            return tipoGrano;

        }
        //session.close();
    }


    public AcopioEntity getAcopioById(Long id){
        AcopioEntity tipoInsumo = new AcopioEntity();
        Session session = null;
        Transaction tx = null;
        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

           Query query = session.createQuery("select x from AcopioEntity x where ucase(acopioId) like ucase(:pId) and acopioFechaBaja is null");
           query.setParameter("pId", id);
           List list = query.list();
           Iterator iter = list.iterator();
           while (iter.hasNext()) {
               tipoInsumo = (AcopioEntity) iter.next();
           }
           tx.rollback();
       }catch (Exception e){

       }finally {
           return tipoInsumo;

       }
        //session.close();
    }

}
