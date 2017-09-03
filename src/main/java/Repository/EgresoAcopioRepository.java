package Repository;

import Conexion.Conexion;
import Datos.AcopioEntity;
import Datos.DetalleEgresoAcopioEntity;
import Datos.EgresoAcopioEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class EgresoAcopioRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<DetalleEgresoAcopioEntity> getAllDetallesByEgresoId(Integer egresoId) {
        List<DetalleEgresoAcopioEntity> listaTipoInsumo = new ArrayList<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            DetalleEgresoAcopioEntity tipoInsumo;
            Query query = session.createQuery("select x from DetalleEgresoAcopioEntity x where x.egresoAcopio.id = :egresoId");
            query.setParameter("egresoId", egresoId);

            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                tipoInsumo = (DetalleEgresoAcopioEntity) iter.next();
                listaTipoInsumo.add(tipoInsumo);
            }
        } catch (Exception e) {

        } finally {
            tx.rollback();
            return listaTipoInsumo;

        }
        //session.close();
    }


    public AcopioEntity getAcopioByCodigo(Integer codigo) {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        AcopioEntity tipoGrano = new AcopioEntity();
        Query query = session.createQuery("select x from AcopioEntity x where ucase(codigo) like ucase(:pNombre) and acopioFechaBaja is null");
        query.setParameter("pNombre", codigo);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            tipoGrano = (AcopioEntity) iter.next();
        }
        //session.close();
        return tipoGrano;
    }


    public EgresoAcopioEntity getEgresoById(Integer id) {
        Session session = null;
        Transaction tx = null;
        EgresoAcopioEntity tipoInsumo = new EgresoAcopioEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select x from EgresoAcopioEntity x where ucase(egresoId) like ucase(:pId) and egresoFechaBaja is null");
            query.setParameter("pId", id);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                tipoInsumo = (EgresoAcopioEntity) iter.next();
            }
            tx.rollback();
        } catch (Exception e) {

        } finally {
            return tipoInsumo;

        }
        //session.close();
    }

}
