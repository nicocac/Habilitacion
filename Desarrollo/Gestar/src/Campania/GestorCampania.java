package Campania;

import Conexion.Coneccion;
import Datos.*;
import Insumo.Insumo;
import Laboreo.DetalleLaboreo;
import Laboreo.MomentoLaboreo;
import Lote.Lote;
import Maquinaria.Maquinaria;
import Repository.CampaniaRepository;
import Repository.LoteCampaniaRepository;
import Repository.LoteRepository;
import TipoInsumo.TipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by jagm on 05/09/2016.
 */
public class GestorCampania {

    Session session;
    java.util.Date fecha = new java.util.Date();
    java.sql.Date fechaActual = new java.sql.Date(fecha.getTime());

    CampaniaRepository campaniaRepository = new CampaniaRepository();
    LoteRepository loteRepository = new LoteRepository();
    LoteCampaniaRepository loteCampaniaRepository = new LoteCampaniaRepository();
//
//    public CampaniaEntity getCampaniaByCnaName(String cnaName){
//        session = Coneccion.getSession();
//        java.util.List list;
//        CampaniaEntity camp = new CampaniaEntity();
//        try {
//            Query query = session.createQuery("select t from CampaniaEntity t where  t.cnaDenominacion= :pCnaName and  cnaFechaBaja is null");
//            query.setParameter("pCnaName", cnaName);
//            list = query.list();
//        } finally {
//            session.close();
//        }
//        Iterator iter = list.iterator();
//        while (iter.hasNext()) {
//            camp = (CampaniaEntity) iter.next();
//        }
//        return camp;
//    }

    public List getCampanias() {
        session = Coneccion.getSession();
        java.util.List list;
        LinkedList retorno = new LinkedList();
        LinkedList lkLotes;
        CampaniaEntity camp;
        Campania campania = new Campania();
        try {
            Query query = session.createQuery("select t from CampaniaEntity t where cnaFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                camp = (CampaniaEntity) iter.next();
                Collection lotes = camp.getLoteCampaniasByCnaId();
                Iterator iterLotes = lotes.iterator();
                lkLotes = new LinkedList();
                while (iterLotes.hasNext()) {
                    // busco por cada lote de campania suy lote correspondiente
                    LoteCampaniaEntity loteCamp = (LoteCampaniaEntity) iterLotes.next();
                    // busco el lote correspondinte a ese lote de campania
                    LoteEntity lote = loteCamp.getLoteByLcpLteId();
                    // genero el lote
                    lkLotes.add(new Lote(lote.getLteDenominacion(), lote.getLteCantMetros(), lote.getLteUbicacion(), lote.getLteFechaDesde(), lote.getLteFechaHasta()));

                }
                campania = new Campania(camp.getCnaDenominacion(), camp.getCnaFechaInicio(), camp.getCnaFechaFinEstimada(), camp.getCnaFechaFinReal(), lkLotes);
                retorno.add(campania);
            }
        } finally {
            session.close();
        }
        return retorno;
    }

    public List getLotesCampania(Campania camp) {
        session = Coneccion.getSession();
        java.util.List list;
        Collection col;
        LinkedList retorno = new LinkedList();
        CampaniaEntity campEnt = new CampaniaEntity();
        LoteCampaniaEntity lcp;
        LoteEntity loteEnt;
        Lote lote;
        try {
            Query queryCamp = session.createQuery("select t from CampaniaEntity t where cnaDenominacion = :pNombre");
            queryCamp.setParameter("pNombre", camp.getDenominacion());
            list = queryCamp.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                campEnt = (CampaniaEntity) iter.next();
            }

            col = campEnt.getLoteCampaniasByCnaId();

            iter = col.iterator();
            while (iter.hasNext()) {
                lcp = (LoteCampaniaEntity) iter.next();
                lote = new Lote(lcp.getLoteByLcpLteId().getLteDenominacion(), lcp.getLoteByLcpLteId().getLteCantMetros(), lcp.getLoteByLcpLteId().getLteUbicacion(), lcp.getLoteByLcpLteId().getLteFechaDesde(), lcp.getLoteByLcpLteId().getLteFechaHasta());
                retorno.add(lote);
            }

        } finally {
            session.close();
        }

        return retorno;

    }



    public List getLotesByCampania(int camp) {
        session = Coneccion.getSession();
        List retorno = new ArrayList();
        List<LoteEntity> listaLotesEntity = loteCampaniaRepository.getLotesByCampaniaId(camp);
        LoteEntity loteEntity;
        Lote lote;

        Iterator iter = listaLotesEntity.iterator();
        while (iter.hasNext()) {
            loteEntity = (LoteEntity) iter.next();
            lote = new Lote(loteEntity.getLteDenominacion(), loteEntity.getLteCantMetros(), loteEntity.getLteUbicacion(), loteEntity.getLteFechaDesde(), loteEntity.getLteFechaHasta());
            retorno.add(lote);
        }
        return retorno;

    }



    public List getMomentos() {
        session = Coneccion.getSession();
        java.util.List list;
        LinkedList retorno = new LinkedList();
        TipoLaboreoEntity te;
        MomentoLaboreo mom;
        try {
            Query query = session.createQuery("select t from TipoLaboreoEntity t where tpoFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                te = (TipoLaboreoEntity) iter.next();
                mom = new MomentoLaboreo(te.getTpoNombre(), te.getTpoDescripcion());
                retorno.add(mom);
            }
        } finally {
            session.close();
        }

        return retorno;
    }

    public List getLotes() {
        session = Coneccion.getSession();
        java.util.List list;
        LoteEntity loteEntity;
        Lote lote;
        LinkedList retorno = new LinkedList();
        try {
            Query query = session.createQuery("select t from LoteEntity t where lteFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                loteEntity = (LoteEntity) iter.next();
                lote = new Lote(loteEntity.getLteDenominacion(), loteEntity.getLteCantMetros(), loteEntity.getLteUbicacion(), loteEntity.getLteFechaDesde(), loteEntity.getLteFechaHasta());
                retorno.add(lote);
            }
        } finally {
            session.close();
        }

        return retorno;
    }


    public void registrarCampania(Campania campania, String tipoOperacion, int cnaId) {
        session = Coneccion.getSession();
        Transaction tx = session.beginTransaction();

        CampaniaEntity campaniaEntity = new CampaniaEntity();

        campaniaEntity.setCnaDenominacion(campania.getDenominacion());
        campaniaEntity.setCnaFechaInicio(campania.getFechaInicio());
        campaniaEntity.setCnaFechaFinEstimada(campania.getFechaFinEstimada());
        campaniaEntity.setCnaFechaFinReal(campania.getFechaFinReal());
        campaniaEntity.setCnaFechaAlta(fechaActual);
        campaniaEntity.setCnaUsuarioAlta("admin");

        for (Lote lote : campania.getLotes()) {
            //Si estamos modificando la campaña borrar y crear de nuevo todos los lotescampanias

            if (!tipoOperacion.equals("Carga")) {
                loteCampaniaRepository.deleteAllByCampania(campaniaEntity.getCnaId());
            }

            LoteCampaniaEntity loteCampaniaEntity = new LoteCampaniaEntity();

            LoteEntity loteEntity = loteRepository.getLoteByDenominacion(lote.getDenominacion());
            loteCampaniaEntity.setLcpFechaInicio(campania.getFechaInicio());
            loteCampaniaEntity.setLcpFechaFin(campania.getFechaFinEstimada());
            loteCampaniaEntity.setLcpFechaAlta(fechaActual);
            loteCampaniaEntity.setLcpUsuarioAlta("admin");
            loteCampaniaEntity.setCampaniaByLcpCnaId(campaniaEntity);
            loteCampaniaEntity.setLoteByLcpLteId(loteEntity);

            session.save(loteCampaniaEntity);
        }

        if (tipoOperacion.equals("Carga")) {
            session.save(campaniaEntity);
        } else {
            campaniaEntity.setCnaId(cnaId);
            session.update(campaniaEntity);
        }

        try {
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
        }
        session.close();


    }

}

