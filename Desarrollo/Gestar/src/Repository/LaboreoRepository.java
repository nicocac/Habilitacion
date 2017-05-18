package Repository;

import Conexion.Coneccion;
import Datos.LaboreoEntity;
import Datos.TipoInsumoEntity;
import Datos.TipoLaboreoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class LaboreoRepository {
//    Session session = Coneccion.getSession();

    public List<TipoInsumoEntity> getAllTipoInsumos(){
        List<TipoInsumoEntity> listaTipoInsumo = new ArrayList<>();
        Session session = Coneccion.getSession();
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


    public LaboreoEntity getLaboreoByNombre(String nombre){
        Session  session = Coneccion.getSession();
        LaboreoEntity tipoLaboreo = new LaboreoEntity();
        Query query = session.createQuery("select x from LaboreoEntity x where ucase(lboNombre) like ucase(:pNombre) and lboFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoLaboreo = (LaboreoEntity) iter.next();
        }
        session.close();
        return tipoLaboreo;
    }


    public LaboreoEntity getLaboreoById(Long id){
        Session session = Coneccion.getSession();
        LaboreoEntity laboreoEntity = new LaboreoEntity();
        Query query = session.createQuery("select x from LaboreoEntity x where ucase(lboId) like ucase(:pId) and lboFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            laboreoEntity = (LaboreoEntity) iter.next();
        }
        session.close();
        return laboreoEntity;
    }

}
