package TipoInsumo;

import Conexion.Coneccion;
import Datos.TipoInsumoEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Iterator;

/**
 * Created by OWNER on 8/28/2016.
 */
public class GestorTipoInsumo {
    Session session = Coneccion.getSession();

    public TipoInsumo getTipoInsumoByName(String nombre){
        TipoInsumo tipoIns = new TipoInsumo("","");
        try {
            TipoInsumoEntity tipo = new TipoInsumoEntity();
            Query query = session.createQuery("select t from TipoInsumoEntity t where ucase(tinNombre) = ucase(:pNombre) and tinFechaBaja is null");
            query.setParameter("pNombre", nombre);
            java.util.List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                tipo = (TipoInsumoEntity) iter.next();
                tipoIns = new TipoInsumo(tipo.getTinNombre(),tipo.getTinDescripcion());
            }
        } finally {
            session.close();
        }

        return tipoIns;
    }

}
