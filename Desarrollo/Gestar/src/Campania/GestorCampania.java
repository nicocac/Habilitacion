package Campania;

import Conexion.Coneccion;
import Datos.*;
import Insumo.Insumo;
import Laboreo.MomentoLaboreo;
import Lote.Lote;
import Maquinaria.Maquinaria;
import TipoInsumo.TipoInsumo;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jagm on 05/09/2016.
 */
public class GestorCampania {

    Session session;

    public List getCampanias(){
        session = Coneccion.getSession();
        java.util.List list;
        LinkedList retorno = new LinkedList();
        LinkedList lkLotes;
        CampaniaEntity camp ;
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

    public List getLotesCampania(Campania camp){
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
                campEnt=(CampaniaEntity)iter.next();
            }

            col = campEnt.getLoteCampaniasByCnaId();

            iter = col.iterator();
            while (iter.hasNext()) {
                lcp=(LoteCampaniaEntity)iter.next();
                lote= new Lote(lcp.getLoteByLcpLteId().getLteDenominacion(),lcp.getLoteByLcpLteId().getLteCantMetros(),lcp.getLoteByLcpLteId().getLteUbicacion(),lcp.getLoteByLcpLteId().getLteFechaDesde(),lcp.getLoteByLcpLteId().getLteFechaHasta());
                retorno.add(lote);
            }

        } finally {
            session.close();}

        return retorno;

    }

    public List getMomentos(){
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
                te = (TipoLaboreoEntity)iter.next();
                mom = new MomentoLaboreo(te.getTpoNombre(),te.getTpoDescripcion());
                retorno.add(mom);
            }
        }finally {
            session.close();}

        return retorno;
    }

    public void registrarLaboreo(LinkedList lotes){

    }


    public List getLotes(){
        session = Coneccion.getSession();
        java.util.List list;
        LoteEntity loteEntity ;
        Lote lote;
        LinkedList retorno = new LinkedList();
        try {
            Query query = session.createQuery("select t from LoteEntity t where lteFechaBaja is null");
            list = query.list();
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                loteEntity=(LoteEntity)iter.next();
                lote= new Lote(loteEntity.getLteDenominacion(), loteEntity.getLteCantMetros(), loteEntity.getLteUbicacion(), loteEntity.getLteFechaDesde(), loteEntity.getLteFechaHasta());
                retorno.add(lote);
            }
        } finally {
            session.close();}

        return retorno;
    }


}

