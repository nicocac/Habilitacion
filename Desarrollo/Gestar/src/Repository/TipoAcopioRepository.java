package Repository;

import Conexion.Coneccion;
import Datos.AcopioEntity;
import Datos.TipoAcopioEntity;
import Datos.TipoGranoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class TipoAcopioRepository {
    Session session = Coneccion.getSession();

    public List<TipoInsumoEntity> getAllTipoInsumos(){
        List<TipoInsumoEntity> listaTipoInsumo = new ArrayList<>();
         session = Coneccion.getSession();
        TipoInsumoEntity tipoInsumo;
        Query query = session.createQuery("select x from TipoInsumoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoInsumoEntity) iter.next();
            listaTipoInsumo.add(tipoInsumo);
        }
        session.close();
        return listaTipoInsumo;
    }


    public TipoAcopioEntity getTipoAcopioByNombre(String nombre){
         session = Coneccion.getSession();
        TipoAcopioEntity tipoGrano = new TipoAcopioEntity();
        Query query = session.createQuery("select x from TipoAcopioEntity x where ucase(tipoAcopioNombre) like ucase(:pNombre) and tipoAcopioFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoGrano = (TipoAcopioEntity) iter.next();
        }
        session.close();
        return tipoGrano;
    }


//    public TipoAcopioEntity getTipoAcopioByAcopioID(Integer acopioId){
//        session = Coneccion.getSession();
//        TipoAcopioEntity tipoAcopio = new TipoAcopioEntity();
//        Query query = session.createQuery("select x from TipoAcopioEntity x " +
//                "where (x.aco) like ucase(:pNombre) and tipoAcopioFechaBaja is null");
//        query.setParameter("pNombre", nombre);
//        List list = query.list();
//        Iterator iter = list.iterator();
//        while (iter.hasNext()) {
//            tipoAcopio = (TipoAcopioEntity) iter.next();
//        }
//        session.close();
//        return tipoAcopio;
//    }


    public TipoInsumoEntity getTipoInsumoById(Long id){
         session = Coneccion.getSession();
        TipoInsumoEntity tipoInsumo = new TipoInsumoEntity();
        Query query = session.createQuery("select x from TipoInsumoEntity x where ucase(tinId) like ucase(:pId) and tinFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoInsumoEntity) iter.next();
        }
        session.close();
        return tipoInsumo;
    }

}
