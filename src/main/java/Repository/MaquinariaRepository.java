package Repository;

import Conexion.Conexion;
import Datos.InsumoEntity;
import Datos.MaquinariaEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class MaquinariaRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<InsumoEntity> getAllInsumos(){
        List<InsumoEntity> listaInsumo = new ArrayList<>();
        Session  session = Conexion.getSessionFactory().getCurrentSession();
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


    public MaquinariaEntity getMaquinariaByNombre(String nombre){
        Session  session = null;
        Transaction tx = null;
        MaquinariaEntity maquinaria = new MaquinariaEntity();

        try {
           session = Conexion.getSessionFactory().getCurrentSession();
           tx = session.beginTransaction();

           Query query = session.createQuery("select x from MaquinariaEntity x where ucase(maqNombre) like ucase(:pNombre) and maqFechaBaja is null");
           query.setParameter("pNombre", nombre);
           List list = query.list();
           Iterator iter = list.iterator();
           while (iter.hasNext()) {
               maquinaria = (MaquinariaEntity) iter.next();
           }
           tx.rollback();
       }catch (Exception e){
           tx.rollback();
       } finally {
           return maquinaria;

       }
        //session.close();
    }


    public MaquinariaEntity getMaquinariaByNombrePlanificacion(String nombre){
        Session  session = null;
//        Transaction tx = null;
        MaquinariaEntity maquinaria = new MaquinariaEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();

            Query query = session.createQuery("select x from MaquinariaEntity x where ucase(maqNombre) like ucase(:pNombre) and maqFechaBaja is null");
            query.setParameter("pNombre", nombre);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                maquinaria = (MaquinariaEntity) iter.next();
            }
//            tx.rollback();
        }catch (Exception e){
//            tx.rollback();
        } finally {
            return maquinaria;

        }
        //session.close();
    }



    public InsumoEntity getInsumoById(Long id){
        Session  session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        InsumoEntity insumo = null;
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insId) like ucase(:pId) and insFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        //session.close();
        return insumo;
    }

}
