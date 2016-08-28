package Laboreo;

import Conexion.Coneccion;
import Datos.InsumoEntity;
import Insumo.Insumo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;

/**
 * Created by OWNER on 8/28/2016.
 */
public class GestorLaboreo {


    Session session;
    public List getInsumos(){
        session = Coneccion.getSession();
        java.util.List list;
        try {
            Query query = session.createQuery("select t from InsumoEntity t where insFechaBaja is null");
            list = query.list();
        } finally {
            session.close();
        }
        return list;
    }

    public List getMaquinaria(){
        session = Coneccion.getSession();
        java.util.List list;
        try {
            Query query = session.createQuery("select t from MaquinariaEntity t where maqFechaBaja is null");
            list = query.list();
        } finally {
            session.close();
        }
        return list;
    }



}
