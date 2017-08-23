package Repository;

import Conexion.Conexion;
import Datos.SolicitudInsumoEntity;
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
public class SolicitudInsumoRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<InsumoEntity> getAllInsumos(){
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


    public InsumoEntity getInsumoByNombre(String nombre){
        Session  session = Conexion.getSessionFactory().getCurrentSession();
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


    public SolicitudInsumoEntity getSolicitudInsumoById(Long id){
        Session   session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        SolicitudInsumoEntity solicitud = new SolicitudInsumoEntity();
        Query query = session.createQuery("select x from SolicitudInsumoEntity x where ucase(siNroSolicitud) like ucase(:pId) and siFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            solicitud = (SolicitudInsumoEntity) iter.next();
        }
        //session.close();
        return solicitud;
    }

}
