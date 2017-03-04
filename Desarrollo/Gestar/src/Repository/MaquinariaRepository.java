package Repository;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Datos.MaquinariaEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class MaquinariaRepository {
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


    public MaquinariaEntity getMaquinariaByNombre(String nombre){
         session = Coneccion.getSession();
        MaquinariaEntity maquinaria = null;
        Query query = session.createQuery("select x from MaquinariaEntity x where ucase(maqNombre) like ucase(:pNombre) and maqFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            maquinaria = (MaquinariaEntity) iter.next();
        }
        session.close();
        return maquinaria;
    }



    public InsumoEntity getInsumoById(Long id){
         session = Coneccion.getSession();
        InsumoEntity insumo = null;
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
