package Repository;

import Conexion.Conexion;
import Datos.TicketPesadaEntity;
import Datos.TransporteEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class TicketPesadaRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<TransporteEntity> getAllTransportes(){
        List<TransporteEntity> listaInsumo = new ArrayList<>();
        Session  session = Conexion.getSessionFactory().openSession();
        TransporteEntity insumo;
        Query query = session.createQuery("select x from TransporteEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (TransporteEntity) iter.next();
            listaInsumo.add(insumo);
        }
        session.close();
        return listaInsumo;
    }


    public TransporteEntity getTransporteByNombre(String nombre){
        Session    session = Conexion.getSessionFactory().openSession();
        TransporteEntity insumo = new TransporteEntity();
        Query query = session.createQuery("select x from TransporteEntity x where ucase(transporteNombre) like ucase(:pNombre) and transporteFechaBaja is null");
        query.setParameter("pNombre", nombre);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (TransporteEntity) iter.next();
        }
        session.close();
        return insumo;
    }


    public TicketPesadaEntity getTicketByOrdenId(Integer id){
        Session   session = Conexion.getSessionFactory().openSession();
        TicketPesadaEntity insumo = new TicketPesadaEntity();
        Query query = session.createQuery("select x from TicketPesadaEntity x where x.ordenTrabajoEntity.id = :pId and fechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (TicketPesadaEntity) iter.next();
        }
        session.close();
        return insumo;
    }

}