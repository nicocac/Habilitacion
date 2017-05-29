package Insumo;

import Conexion.Conexion;
import Datos.InsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * Created by OWNER on 10/2/2016.
 */
public class GestorInsumo {
    Session session;
    public InsumoEntity getInsumoByName(String pNombre) {
        session = Conexion.getSessionFactory().openSession();
        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select t from InsumoEntity t where t.insNombre = :pInsNombre and t.insFechaBaja is null");
        query.setParameter("pInsNombre", pNombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        return insumo;
    }

}
