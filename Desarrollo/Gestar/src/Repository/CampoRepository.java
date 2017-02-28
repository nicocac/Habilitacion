package Repository;

import Conexion.Coneccion;
import Datos.CampoEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class CampoRepository {
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


    public CampoEntity getCampoByNombre(String nombre){
         session = Coneccion.getSession();
        CampoEntity campo = new CampoEntity();
        Query query = session.createQuery("select x from CampoEntity x where ucase(cpoNombre) like ucase(:pNombre) and cpoFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            campo = (CampoEntity) iter.next();
        }
        session.close();
        return campo;
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