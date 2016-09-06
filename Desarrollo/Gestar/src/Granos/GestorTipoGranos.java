package Granos;

import Conexion.Coneccion;
import Datos.TipoGranoEntity;
import Datos.TipoInsumoEntity;
import TipoInsumo.TipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;

/**
 * Created by jagm on 05/09/2016.
 */
public class GestorTipoGranos {

    Session session = Coneccion.getSession();

    public TipoGrano getTipoGranoByName(String nombre){
        TipoGrano tipoGr = new TipoGrano("","");
        try {
            TipoGranoEntity tipo = new TipoGranoEntity();
            Query query = session.createQuery("select t from TipoGranoEntity t where ucase(tgrNombre) = ucase(:pNombre) and tgrFechaBaja is null");
            query.setParameter("pNombre", nombre);
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                tipo = (TipoGranoEntity) iter.next();
                tipoGr = new TipoGrano(tipo.getTgrNombre(),tipo.getTgrDescripcion());
            }
        } finally {
            session.close();
        }

        return tipoGr;
    }
}
