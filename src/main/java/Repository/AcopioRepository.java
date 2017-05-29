package Repository;

import Conexion.Conexion;
import Datos.AcopioEntity;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

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
        Session session = Conexion.getSessionFactory().openSession();
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


    public AcopioEntity getAcopioByCodigo(Integer codigo){
        Session session = Conexion.getSessionFactory().openSession();
        AcopioEntity tipoGrano = new AcopioEntity();
        Query query = session.createQuery("select x from AcopioEntity x where ucase(codigo) like ucase(:pNombre) and acopioFechaBaja is null");
        query.setParameter("pNombre", codigo);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoGrano = (AcopioEntity) iter.next();
        }
        session.close();
        return tipoGrano;
    }


    public AcopioEntity getAcopioById(Long id){
        Session session = Conexion.getSessionFactory().openSession();
        AcopioEntity tipoInsumo = new AcopioEntity();
        Query query = session.createQuery("select x from AcopioEntity x where ucase(acopioId) like ucase(:pId) and acopioFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (AcopioEntity) iter.next();
        }
        session.close();
        return tipoInsumo;
    }

}
