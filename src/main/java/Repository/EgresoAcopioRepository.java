package Repository;

import Conexion.Conexion;
import Datos.AcopioEntity;
import Datos.DetalleEgresoAcopioEntity;
import Datos.EgresoAcopioEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class EgresoAcopioRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<DetalleEgresoAcopioEntity> getAllDetallesByEgresoId(Integer egresoId){
        List<DetalleEgresoAcopioEntity> listaTipoInsumo = new ArrayList<>();
        Session  session = Conexion.getSessionFactory().openSession();
        DetalleEgresoAcopioEntity tipoInsumo;
        Query query = session.createQuery("select x from DetalleEgresoAcopioEntity x where x.egresoAcopio.id = :egresoId");
        query.setParameter("egresoId", egresoId);

        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (DetalleEgresoAcopioEntity) iter.next();
            listaTipoInsumo.add(tipoInsumo);
        }
        session.close();
        return listaTipoInsumo;
    }


    public AcopioEntity getAcopioByCodigo(Integer codigo){
        Session   session = Conexion.getSessionFactory().openSession();
        AcopioEntity tipoGrano = new AcopioEntity();
        Query query = session.createQuery("select x from AcopioEntity x where ucase(codigo) like ucase(:pNombre) and acopioFechaBaja is null");
        query.setParameter("pNombre", codigo);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoGrano = (AcopioEntity) iter.next();
        }
        session.close();
        return tipoGrano;
    }


    public EgresoAcopioEntity getEgresoById(Integer id){
        Session    session = Conexion.getSessionFactory().openSession();
        EgresoAcopioEntity tipoInsumo = new EgresoAcopioEntity();
        Query query = session.createQuery("select x from EgresoAcopioEntity x where ucase(egresoId) like ucase(:pId) and egresoFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoInsumo = (EgresoAcopioEntity) iter.next();
        }
        session.close();
        return tipoInsumo;
    }

}