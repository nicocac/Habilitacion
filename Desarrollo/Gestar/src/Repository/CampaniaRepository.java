package Repository;

import Conexion.Coneccion;
import Datos.CampaniaEntity;
import Datos.InsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class CampaniaRepository {

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


    public CampaniaEntity getCampaniaByNombre (String nombre){
        session = Coneccion.getSession();
        java.util.List list;
        CampaniaEntity camp = new CampaniaEntity();
        try {
            Query query = session.createQuery("select t from CampaniaEntity t where  t.cnaDenominacion= :pCnaName and  cnaFechaBaja is null");
            query.setParameter("pCnaName", nombre);
            list = query.list();
        } finally {
            session.close();
        }
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            camp = (CampaniaEntity) iter.next();
        }
        return camp;
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
