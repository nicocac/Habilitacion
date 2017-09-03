package Maquinaria;

import Conexion.Conexion;
import Datos.MaquinariaEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;

/**
 * Created by OWNER on 8/28/2016.
 */
public class GestorMaquinaria {
    Session session = Conexion.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();


    public MaquinariaEntity getMaquinariaByName(String nombre){
        MaquinariaEntity maq = new MaquinariaEntity();
        try {
            Query query = session.createQuery("select t from MaquinariaEntity t where ucase(maqNombre) = ucase(:pNombre) and maqFechaBaja is null");
            query.setParameter("pNombre", nombre);
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                maq = (MaquinariaEntity) iter.next();
            }
        } finally {
            tx.rollback();
        }
        return maq;
    }

}
