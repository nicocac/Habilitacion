package Repository;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.LoteEntity;
import Laboreo.MomentoLaboreo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class LoteRepository {
    Session session = Coneccion.getSession();

    public List<InsumoEntity> getAllInsumos(){
        List<InsumoEntity> listaInsumo = new ArrayList<>();
         session = Coneccion.getSession();
        InsumoEntity insumo;
        Query query = session.createQuery("select x from InsumoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
            listaInsumo.add(insumo);
        }
        session.close();
        return listaInsumo;
    }


    public LoteEntity getLoteByDenominacion(String denominacion){
         session = Coneccion.getSession();
        LoteEntity lote = new LoteEntity();
        Query query = session.createQuery("select x from LoteEntity x where ucase(lteDenominacion) like ucase(:pNombre) and lteFechaBaja is null");
        query.setParameter("pNombre", denominacion);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            lote = (LoteEntity) iter.next();
        }
        session.close();
        return lote;
    }




    public InsumoEntity getInsumoById(Long id){
         session = Coneccion.getSession();
        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insId) like ucase(:pId) and insFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        session.close();
        return insumo;
    }

}
