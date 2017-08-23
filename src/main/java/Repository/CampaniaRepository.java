package Repository;

import Conexion.Conexion;
import Datos.CampaniaEntity;
import Datos.InsumoEntity;
import Datos.LoteCampaniaEntity;
import Datos.LoteEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jagm on 07/10/2016.
 */
public class CampaniaRepository {

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


    public CampaniaEntity getCampaniaByNombre(String nombre) {

        Session session = null;
        Transaction tx = null;

        java.util.List list;
        CampaniaEntity camp = new CampaniaEntity();
        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("select t from CampaniaEntity t where  t.cnaDenominacion= :pCnaName and  cnaFechaBaja is null");
            query.setParameter("pCnaName", nombre);
            list = query.list();

            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                camp = (CampaniaEntity) iter.next();
            }
            tx.rollback();
        } catch (Exception e) {
            session.close();

        } finally {
            return camp;

        }
    }





    public CampaniaEntity getCampaniaByNombreSinTX(String nombre) {

        Session session = null;
//        Transaction tx = null;

        java.util.List list;
        CampaniaEntity camp = new CampaniaEntity();
        try {
            session = Conexion.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();
            Query query = session.createQuery("select t from CampaniaEntity t where  t.cnaDenominacion= :pCnaName and  cnaFechaBaja is null");
            query.setParameter("pCnaName", nombre);
            list = query.list();

            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                camp = (CampaniaEntity) iter.next();
            }
//            tx.rollback();
        } catch (Exception e) {
            session.close();

        } finally {
            return camp;

        }
    }


    public CampaniaEntity getCampaniaById(Integer id) {
        Session session = null;
        Transaction tx = null;
        CampaniaEntity insumo = new CampaniaEntity();


        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select x from CampaniaEntity x where ucase(cnaId) like ucase(:pId) and cnaFechaBaja is null");
            query.setParameter("pId", id);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (CampaniaEntity) iter.next();
            }
            tx.rollback();
        } catch (Exception ex) {
            tx.rollback();
        } finally {
            //session.close();
            return insumo;

        }
        //session.close();
    }




    public CampaniaEntity getCampaniaByIdEnBaja(Integer id) {
        Session session = null;
        Transaction tx = null;
        CampaniaEntity insumo = new CampaniaEntity();


        try {
            session = Conexion.getSessionFactory().getCurrentSession();
//            tx = session.beginTransaction();

            Query query = session.createQuery("select x from CampaniaEntity x where ucase(cnaId) like ucase(:pId) and cnaFechaBaja is null");
            query.setParameter("pId", id);
            List list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                insumo = (CampaniaEntity) iter.next();
            }
//            tx.rollback();
        } catch (Exception ex) {
//            tx.rollback();
        } finally {
            //session.close();
            return insumo;

        }
        //session.close();
    }


    public void darBajaCampania(Integer camId, Date fecha) {
        Session session = null;
        Transaction tx = null;

        try {
            session = Conexion.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            CampaniaEntity campaniaEntity = getCampaniaByIdEnBaja(camId);
            campaniaEntity.setCnaFechaUltMod(fecha);
            campaniaEntity.setCnaUsuarioUltMod("Admin que Finaliza la orden");
            campaniaEntity.setEstado("CANCELADA");
            Collection<LoteCampaniaEntity> ltc = campaniaEntity.getLoteCampaniasByCnaId();

            for (LoteCampaniaEntity lot : ltc) {
                LoteEntity loteEntity = lot.getLoteByLcpLteId();
                loteEntity.setEstado("LIBRE");
                session.merge(loteEntity);
            }

            session.merge(campaniaEntity);

            tx.commit();
            JOptionPane.showMessageDialog(null, "La Campa?a fue cancelada en forma definitiva con exito.");
//                    session.flush();
            //session.close();
//                dispose();


        } catch (Exception ex) {
            tx.rollback();
            JOptionPane.showMessageDialog(null, "Ocurrio un error al finalizar la Campaa : " + ex.toString());
        } finally {
            //session.close();
        }
//        return insumo;
    }


}
