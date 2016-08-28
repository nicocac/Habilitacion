package Maquinaria;

import Conexion.Coneccion;
import Datos.MaquinariaEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;

/**
 * Created by OWNER on 8/28/2016.
 */
public class GestorMaquinaria {
    Session session = Coneccion.getSession();

    public Maquinaria getMaquinariaByName(String nombre){
        Maquinaria maquinaria = new Maquinaria();
        try {
            MaquinariaEntity maq = new MaquinariaEntity();
            Query query = session.createQuery("select t from MaquinariaEntity t where ucase(maqNombre) = ucase(:pNombre) and maqFechaBaja is null");
            query.setParameter("pNombre", nombre);
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                maq = (MaquinariaEntity) iter.next();
                maquinaria = new Maquinaria(maq.getMaqNombre(),maq.getMaqDescripcion(),maq.getMaqMarca(),maq.getMaqModelo(),maq.getMaqAnioFabricacion(),new TipoMaquinaria(maq.getTipoMaquinariaByMaqTmaqId().getTmaNombre(),maq.getTipoMaquinariaByMaqTmaqId().getTmaDescripcion()),new EstadoMaquinaria(maq.getTipoEstadoMaquinariaByMaqTestadoId().getTeMaNombre(),maq.getTipoEstadoMaquinariaByMaqTestadoId().getTeMaDescripcion()));
            }
        } finally {
            session.close();
        }

        return maquinaria;
    }

}
