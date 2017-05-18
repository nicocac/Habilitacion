package Repository;

import Conexion.Coneccion;
import Datos.DetalleOrdenEntity;
import Datos.InsumoEntity;
import Datos.OrdenTrabajoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class OrdenRepository {
//    Session session = Coneccion.getSession();

    public List<OrdenTrabajoEntity> getAllOrdenes(){
        List<OrdenTrabajoEntity> listaInsumo = new ArrayList<>();
        Session  session = Coneccion.getSession();
        OrdenTrabajoEntity insumo;
        Query query = session.createQuery("select x from OrdenTrabajoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (OrdenTrabajoEntity) iter.next();
            listaInsumo.add(insumo);
        }
        session.close();
        return listaInsumo;
    }


    public List<DetalleOrdenEntity> getAllDetalleOrdenesByOrdenID(Integer ordenID){
        List<DetalleOrdenEntity> listaInsumo = new ArrayList<>();
        Session session = Coneccion.getSession();
        DetalleOrdenEntity insumo;
        Query query = session.createQuery("select x from DetalleOrdenEntity x where x.orden = (:ordenID) ");
        query.setParameter("ordenID", ordenID);

        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (DetalleOrdenEntity) iter.next();
            listaInsumo.add(insumo);
        }
        session.close();
        return listaInsumo;
    }


    public int deleteAllDetalleOrdenesByOrdenID(Integer ordenID){
        Boolean flag = false;
        int result = 0;
        Session  session = Coneccion.getSession();
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
        session.close();
        return result;
    }


    public InsumoEntity getInsumoByNombre(String nombre){
        Session   session = Coneccion.getSession();
        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insNombre) like ucase(:pNombre) and insFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        session.close();
        return insumo;
    }


    public OrdenTrabajoEntity getOrdenById(Integer id){
        Session   session = Coneccion.getSession();
        OrdenTrabajoEntity insumo = new OrdenTrabajoEntity();
        Query query = session.createQuery("select x from OrdenTrabajoEntity x " +
                "where ucase(id) like ucase(:pId) and fechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (OrdenTrabajoEntity) iter.next();
        }
        session.close();
        return insumo;
    }

}
