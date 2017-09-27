package Repository;

import Campania.Campania;
import Conexion.Conexion;
import Datos.CampaniaEntity;
import Datos.InsumoEntity;
import Datos.LoteCampaniaEntity;
import Datos.LoteEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class LoteRepository {
//    Session session = Conexion.getSessionFactory().openSession()

    public List<InsumoEntity> getAllInsumos() {
        List<InsumoEntity> listaInsumo = new ArrayList<>();
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        InsumoEntity insumo;
        Query query = session.createQuery("select x from InsumoEntity x");
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
            listaInsumo.add(insumo);
        }
        //session.close();
        return listaInsumo;
    }


    public LoteEntity getLoteByDenominacion(String denominacion) {
        Session session = null;
        Transaction tx = null;
        LoteEntity lote = new LoteEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select x from LoteEntity x where ucase(lteDenominacion) like ucase(:pNombre) and lteFechaBaja is null");
            query.setParameter("pNombre", denominacion);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                lote = (LoteEntity) iter.next();
            }
            tx.rollback();

            //session.close();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            return lote;

        }
    }


    public LoteEntity getLoteByDenominacionEnRegistrarCampania(String denominacion) {
        Session session = null;
        Transaction tx = null;
        LoteEntity lote = new LoteEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select x from LoteEntity x where ucase(lteDenominacion) like ucase(:pNombre) and lteFechaBaja is null");
            query.setParameter("pNombre", denominacion);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                lote = (LoteEntity) iter.next();
            }
            tx.rollback();

            //session.close();
        } catch (Exception e) {
//            tx.rollback();
        } finally {

            return lote;

        }
    }


    public boolean grabarLoteCampEntities(LoteEntity lote, Campania campania, java.sql.Date fechaActual, CampaniaEntity campaniaEntity, boolean flag) {
        Session session = null;
        Transaction tx = null;
        LoteCampaniaEntity loteCampaniaEntity = new LoteCampaniaEntity();

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            lote.setEstado("OCUPADO");
            session.update(lote);

            loteCampaniaEntity.setLcpFechaInicio(campania.getFechaInicio());
            loteCampaniaEntity.setLcpFechaFin(campania.getFechaFinEstimada());
            loteCampaniaEntity.setLcpFechaAlta(fechaActual);
            loteCampaniaEntity.setLcpUsuarioAlta("admin");
            loteCampaniaEntity.setCampaniaByLcpCnaId(campaniaEntity);
            loteCampaniaEntity.setLoteByLcpLteId(lote);

            session.save(loteCampaniaEntity);
            if (!flag){
                session.save(campaniaEntity);
            } else {
                session.merge(campaniaEntity);
            }
            flag = true;
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            return flag;

        }
    }


    public InsumoEntity getInsumoById(Long id) {
        Session session = Conexion.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        InsumoEntity insumo = new InsumoEntity();
        Query query = session.createQuery("select x from InsumoEntity x where ucase(insId) like ucase(:pId) and insFechaBaja is null");
        query.setParameter("pId", id);
        List list = query.list();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            insumo = (InsumoEntity) iter.next();
        }
        //session.close();
        return insumo;
    }

}
