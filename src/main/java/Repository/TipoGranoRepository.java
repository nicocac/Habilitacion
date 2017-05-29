package Repository;

import Conexion.Conexion;
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
public class TipoGranoRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<TipoGranoEntity> getAllTipoGranos(){
        List<TipoGranoEntity> listaTipoInsumo = new ArrayList<>();
        Session  session = Conexion.getSessionFactory().openSession();
        TipoGranoEntity tipoInsumo;
        Query query = session.createQuery("select x from TipoGranoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (TipoGranoEntity) iter.next();
            listaTipoInsumo.add(tipoInsumo);
        }
        session.close();
        return listaTipoInsumo;
    }


    public TipoGranoEntity getTipoGranoByNombre(String nombre){
        Session  session = Conexion.getSessionFactory().openSession();
        TipoGranoEntity tipoGrano = new TipoGranoEntity();
        Query query = session.createQuery("select x from TipoGranoEntity x where ucase(tgrNombre) like ucase(:pNombre) and tgrFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoGrano = (TipoGranoEntity) iter.next();
        }
        session.close();
        return tipoGrano;
    }


    public TipoInsumoEntity getTipoInsumoById(Long id){
        Session  session = Conexion.getSessionFactory().openSession();
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
