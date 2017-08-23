package TipoInsumo;

import Conexion.Conexion;
import org.hibernate.Session;

/**
 * Created by OWNER on 8/28/2016.
 */
public class GestorTipoInsumo {
    Session session = Conexion.getSessionFactory().getCurrentSession();

//    public TipoInsumo getTipoInsumoByName(String nombre){
//        TipoInsumo tipoIns = new TipoInsumo("","");
//        try {
//            TipoInsumoEntity tipo = new TipoInsumoEntity();
//            Query query = session.createQuery("select t from TipoInsumoEntity t where ucase(tinNombre) = ucase(:pNombre) and tinFechaBaja is null");
//            query.setParameter("pNombre", nombre);
//            java.util.List list = query.list();
//            Iterator iter = list.iterator();
//            while (iter.hasNext()) {
//                tipo = (TipoInsumoEntity) iter.next();
//                tipoIns = new TipoInsumo(tipo.getTinNombre(),tipo.getTinDescripcion());
//            }
//        } finally {
//            //session.close();
//        }
//
//        return tipoIns;
//    }

}
