package Repository;

import Conexion.Coneccion;
import Datos.TipoInsumoEntity;
import Datos.TipoTransporteEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class TipoTransporteRepository {
    Session session = Coneccion.getSession();

    public List<TipoTransporteEntity> getAllTipoTransportes(){
        List<TipoTransporteEntity> listaTipoInsumo = new ArrayList<>();
         session = Coneccion.getSession();
        TipoTransporteEntity tipoInsumo;
        Query query = session.createQuery("select x from TipoTransporteEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoTransporteEntity) iter.next();
            listaTipoInsumo.add(tipoInsumo);
        }
        session.close();
        return listaTipoInsumo;
    }


    public TipoTransporteEntity getTipoTransporteByNombre(String nombre){
         session = Coneccion.getSession();
        TipoTransporteEntity tipoInsumo = new TipoTransporteEntity();
        Query query = session.createQuery("select x from TipoTransporteEntity x where ucase(tinNombre) like ucase(:pNombre) and tinFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoTransporteEntity) iter.next();
        }
        session.close();
        return tipoInsumo;
    }


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
