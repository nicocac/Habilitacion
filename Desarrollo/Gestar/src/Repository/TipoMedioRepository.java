package Repository;

import Conexion.Coneccion;
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
public class TipoMedioRepository {
//    Session session = Coneccion.getSession();

    public List<TipoInsumoEntity> getAllTipoInsumos(){
        List<TipoInsumoEntity> listaTipoInsumo = new ArrayList<>();
        Session    session = Coneccion.getSession();
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


    public TipoGranoEntity getTipoGranoByNombre(String nombre){
        Session  session = Coneccion.getSession();
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
        Session  session = Coneccion.getSession();
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
